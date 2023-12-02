package gg.zyro.internal.protocol.connection;

import gg.zyro.internal.protocol.packet.processing.MasterProcessor;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.Nullable;

import java.net.SocketAddress;
import java.nio.channels.AlreadyBoundException;
import java.util.function.Consumer;

@FieldDefaults(level = AccessLevel.PROTECTED)
public abstract class AbstractServerConnection extends Connection {

    EventLoopGroup boss;
    EventLoopGroup worker;

    ServerBootstrap bootstrap;

    private ChannelFuture future;
    private ServerSocketChannel channel;

    public AbstractServerConnection(SocketAddress addr) {
        super(addr, true);

        init();
    }

    protected void checkBindAvailability() throws Exception {
        if (isConnected()) {
            throw new Exception(new AlreadyBoundException());
        }
    }

    public void bindAsynchronous(@NonNull Consumer<Exception> errorHandler, @Nullable Runnable success) {
        try {
            checkBindAvailability();

            future = bootstrap.bind();

            future.addListener(future -> {
                if (future.isSuccess()) {
                    channel = (ServerSocketChannel) this.future.channel();

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

    public void bindSynchronized() throws Exception {
        checkBindAvailability();

        try {
            future = bootstrap.bind().sync();

            if (future.isSuccess()) {
                channel = (ServerSocketChannel) future.channel();
            } else {
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

    public boolean isConnected() {
        return channel != null && channel.isActive();
    }

    public void closeConnection() {
        if (isConnected()) {
            closeChannel();
        } else if (future != null) {
            interruptFuture();
        }
    }

    @Override
    public void init() {
        super.init();

        boss = new NioEventLoopGroup(2);
        worker = new NioEventLoopGroup();
        bootstrap = new ServerBootstrap()
                .localAddress(socketAddress)
                .channelFactory(NioServerSocketChannel::new)
                .childHandler(channelInitializer)
                .group(boss, worker);
    }

    @Override
    public MasterProcessor newMasterProcessor(SocketChannel socketChannel) {
        return newClientConnection(socketChannel);
    }

    public abstract MasterProcessor newClientConnection(final SocketChannel socketChannel);
}
