package cc.ranked.internal.connector.bungee.connection;

import cc.ranked.internal.protocol.connection.AbstractClientConnection;
import cc.ranked.internal.protocol.connection.AbstractRemoteServerConnection;
import io.netty.channel.socket.SocketChannel;

import java.net.SocketAddress;

public class ConnectorLocalServerConnection extends AbstractClientConnection {
    public ConnectorLocalServerConnection(SocketAddress socketAddress) {
        super(socketAddress);
    }

    @Override
    public AbstractRemoteServerConnection newServerConnection(SocketChannel socketChannel) {
        return new RemoteServerConnection(socketChannel);
    }
}
