package cc.ranked.internal.connector.bungee.connection;

import cc.ranked.internal.protocol.connection.AbstractRemoteServerConnection;
import io.netty.channel.socket.SocketChannel;

public class RemoteServerConnection extends AbstractRemoteServerConnection {
    public RemoteServerConnection(SocketChannel channel) {
        super(channel);
    }

    @Override
    protected void onDisconnect() {

    }
}
