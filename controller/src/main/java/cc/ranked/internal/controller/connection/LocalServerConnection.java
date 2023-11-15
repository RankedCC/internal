package cc.ranked.internal.controller.connection;

import cc.ranked.internal.protocol.connection.AbstractServerConnection;
import cc.ranked.internal.protocol.packet.processing.MasterProcessor;
import io.netty.channel.socket.SocketChannel;

import java.net.SocketAddress;

public class LocalServerConnection extends AbstractServerConnection {
    public LocalServerConnection(SocketAddress addr) {
        super(addr);
    }

    @Override
    public MasterProcessor newClientConnection(SocketChannel socketChannel) {
        return new RemoteClientConnection(socketChannel);
    }
}
