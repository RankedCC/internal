package cc.ranked.internal.protocol.connection;

import cc.ranked.internal.protocol.packet.processing.ClientPacketProcessor;
import io.netty.channel.socket.SocketChannel;

public abstract class AbstractRemoteServerConnection
        extends AbstractRemoteConnection
        implements ClientPacketProcessor {

    public AbstractRemoteServerConnection(SocketChannel channel) {
        super(channel);
    }

//    @Override
//    public void process(@NotNull ConnectionProcessorContext ctx) {
//        ctx.callProcess(this);
//    }
}
