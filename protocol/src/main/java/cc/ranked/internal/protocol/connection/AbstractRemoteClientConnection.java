package cc.ranked.internal.protocol.connection;

import cc.ranked.internal.protocol.packet.processing.ServerPacketProcessor;
import io.netty.channel.socket.SocketChannel;

public abstract class AbstractRemoteClientConnection
        extends AbstractRemoteConnection
        implements ServerPacketProcessor {

    public AbstractRemoteClientConnection(final SocketChannel channel) {
        super(channel);
    }

//    @Override
//    public void process(@NotNull ConnectionProcessorContext ctx) {
//        ctx.callProcess(this);
////        handlePacket(ctx);
//    }


}
