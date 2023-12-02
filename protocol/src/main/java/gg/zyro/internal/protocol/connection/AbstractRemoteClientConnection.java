package gg.zyro.internal.protocol.connection;

import gg.zyro.internal.protocol.packet.processing.ServerPacketProcessor;
import io.netty.channel.socket.SocketChannel;

public abstract class AbstractRemoteClientConnection
        extends AbstractRemoteConnection
        implements ServerPacketProcessor {

    public AbstractRemoteClientConnection(final SocketChannel channel) {
        super(channel);
    }
}
