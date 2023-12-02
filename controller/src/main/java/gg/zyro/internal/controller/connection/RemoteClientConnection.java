package gg.zyro.internal.controller.connection;

import gg.zyro.internal.controller.Controller;
import gg.zyro.internal.protocol.connection.AbstractRemoteClientConnection;
import gg.zyro.internal.protocol.packet.impl.C1ClientDataPacket;
import gg.zyro.internal.protocol.packet.impl.C2TestPacket;
import gg.zyro.internal.protocol.util.ClientType;
import io.netty.channel.socket.SocketChannel;
import lombok.val;
import org.jetbrains.annotations.NotNull;

public class RemoteClientConnection extends AbstractRemoteClientConnection {
    public RemoteClientConnection(SocketChannel channel) {
        super(channel);
        // When the client connects, we create dis shit
        Controller.INSTANCE.connectionManager().onClientConnected(this);
    }

    @Override
    public void process(@NotNull C1ClientDataPacket packet) {
        System.out.println("c1clientdata");
        val type = packet.clientType();
        ClientConnectionManager.INSTANCE.switchType(
                packet.clientUUID(),
                type
        ); // here we switch from UNDEFINED to BUNGEE/BUKKIT

        System.out.println(packet.clientUUID() + " switched their type to " + type.name());
    }

    @Override
    public void process(@NotNull C2TestPacket packet) {
        System.out.println(packet.getMessage());
    }

    @Override
    protected void onDisconnect() {
        System.out.println("disconnected");
    }
}
