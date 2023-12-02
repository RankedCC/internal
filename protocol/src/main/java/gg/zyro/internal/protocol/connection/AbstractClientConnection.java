package gg.zyro.internal.protocol.connection;

import gg.zyro.internal.protocol.packet.Packet;
import gg.zyro.internal.protocol.packet.processing.MasterProcessor;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.NonNull;
import org.jetbrains.annotations.Nullable;

import java.net.SocketAddress;
import java.nio.channels.AlreadyConnectedException;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public abstract class AbstractClientConnection extends Connection {

    EventLoopGroup worker;
    Bootstrap bootstrap;
    AbstractRemoteServerConnection channel;
    private ChannelFuture future;

    public AbstractClientConnection(SocketAddress socketAddress) {
        super(socketAddress, false);

        init();
    }

    @Override
    public void init() {
        super.init();

        worker = new NioEventLoopGroup();
        bootstrap = new Bootstrap()
                .option(ChannelOption.TCP_NODELAY, true)
                .remoteAddress(socketAddress)
                .channel(NioSocketChannel.class)
                .handler(channelInitializer)
                .group(worker);
    }

    protected void ensureConnectionAvailability() throws Exception {
        if (isConnected()) {
            throw new Exception(new AlreadyConnectedException());
        }
    }

    public void connectAsynchronous(@NonNull Consumer<Exception> errorHandler, @Nullable Runnable success) {
        try {
            ensureConnectionAvailability();

            future = bootstrap.connect();

            future.addListener(future -> {
                if (future.isSuccess()) {
                    if (success != null) {
                        success.run();
                    }
                } else {
                    errorHandler.accept(new Exception(future.cause()));
                }

                this.future = null;
            });
        } catch (Exception e) {
            errorHandler.accept(new Exception(e));
        }
    }

    public void connectSynchronized() throws Exception {
        ensureConnectionAvailability();

        try {
            future = bootstrap.connect().sync();

            if (!future.isSuccess()) {
                throw future.cause();
            }
        } catch (Throwable t) {
            throw new Exception(t);
        } finally {
            future = null;
        }
    }

    protected void closeChannel() {
        channel.close();
    }

    protected void interruptFuture() {
        future.cancel(true);
        future = null;
    }

    public void closeConnection() {
        if (isConnected()) {
            closeChannel();
        } else if (future != null) {
            interruptFuture();
        }
    }

    public boolean isConnected() {
        return channel != null && channel.isActive();
    }

    private boolean reconnecting;

    public void reconnect() {
        if (reconnecting) {
            return;
        }

        reconnecting = true;
        scheduleReconnect();
    }

    protected void scheduleReconnect() {
        System.out.println("Reconnecting in 6 seconds...");
        worker.schedule(this::doReconnect, 6, TimeUnit.SECONDS);
    }

    protected void doReconnect() {
        try {
            connectSynchronized();

            reconnecting = false;

        } catch (Exception e) {
            System.out.println("Unable to reconnect: " + e);

            scheduleReconnect();
        }
    }

    @Override
    public MasterProcessor newMasterProcessor(SocketChannel socketChannel) {
        return this.channel = newServerConnection(socketChannel);
    }

    public void sendPacket(Packet<?> packet) {
        if (isConnected()) {
            System.out.println("connected");
            channel.sendPacket(packet);
        }
    }

    public abstract AbstractRemoteServerConnection newServerConnection(final SocketChannel socketChannel);
}
