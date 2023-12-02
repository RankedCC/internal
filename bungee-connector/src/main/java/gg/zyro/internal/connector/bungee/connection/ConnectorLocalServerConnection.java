package gg.zyro.internal.connector.bungee.connection;

import gg.zyro.internal.protocol.connection.AbstractClientConnection;
import gg.zyro.internal.protocol.connection.AbstractRemoteServerConnection;
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
