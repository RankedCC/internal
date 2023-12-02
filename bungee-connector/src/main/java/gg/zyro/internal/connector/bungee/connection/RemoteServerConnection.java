package gg.zyro.internal.connector.bungee.connection;

import gg.zyro.internal.connector.bungee.BungeeConnector;
import gg.zyro.internal.protocol.connection.AbstractRemoteServerConnection;
import gg.zyro.internal.protocol.packet.impl.C1ClientDataPacket;
import gg.zyro.internal.protocol.packet.impl.S0HelloPacket;
import gg.zyro.internal.protocol.util.ClientType;
import io.netty.channel.socket.SocketChannel;
import org.jetbrains.annotations.NotNull;

public class RemoteServerConnection extends AbstractRemoteServerConnection {
    public RemoteServerConnection(SocketChannel channel) {
        super(channel);
    }

    @Override
    public void process(@NotNull S0HelloPacket packet) {
        BungeeConnector.INSTANCE.clientData(packet.data()); // take the uuid
        sendPacket(new C1ClientDataPacket(ClientType.BUNGEE, packet.data().uuid()));
    }

    @Override
    protected void onDisconnect() {

    }
}
