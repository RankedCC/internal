package cc.ranked.internal.protocol.connection;

import cc.ranked.internal.protocol.packet.processing.MasterProcessor;
import cc.ranked.internal.protocol.pipeline.Pipeline;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.net.SocketAddress;

@RequiredArgsConstructor
public abstract class Connection {

    protected ChannelInitializer<SocketChannel> channelInitializer;
    protected final SocketAddress socketAddress;
    protected final boolean server;

    public void init() {
        initChannel();
    }

    protected void initChannel() {
        channelInitializer = new ChannelInitializer<>() {
            @Override
            protected void initChannel(@NotNull SocketChannel ch) throws Exception {
                initPipeline(ch);
            }
        };
    }

    private void initPipeline(final SocketChannel socketChannel) {
        new Pipeline(this, socketChannel, server);
    }

    public abstract MasterProcessor newMasterProcessor(SocketChannel socketChannel);
}
