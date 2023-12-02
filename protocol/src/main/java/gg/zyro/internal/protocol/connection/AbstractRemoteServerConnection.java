package gg.zyro.internal.protocol.connection;

import gg.zyro.internal.protocol.packet.processing.ClientPacketProcessor;
import io.netty.channel.socket.SocketChannel;

public abstract class AbstractRemoteServerConnection
        extends AbstractRemoteConnection
        implements ClientPacketProcessor {

    public AbstractRemoteServerConnection(SocketChannel channel) {
        super(channel);
    }

}
