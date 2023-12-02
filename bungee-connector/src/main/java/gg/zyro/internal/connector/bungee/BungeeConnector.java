package gg.zyro.internal.connector.bungee;

import gg.zyro.internal.connector.bungee.connection.ConnectorLocalServerConnection;
import gg.zyro.internal.protocol.packet.impl.C2TestPacket;
import gg.zyro.internal.protocol.server.ClientData;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.val;

import java.net.InetSocketAddress;

@Accessors(fluent = true)
@Setter
@Getter
public class BungeeConnector {
    public static BungeeConnector INSTANCE;
    private ClientData clientData;

    public void run() throws Exception {
        INSTANCE = this;
        val localConnection = new ConnectorLocalServerConnection(new InetSocketAddress(
                "localhost",
                1338
        ));

        localConnection.connectSynchronized();
        //localConnection.sendPacket(new C2TestPacket("Hello!"));
    }

    public static void main(String[] args) throws Exception {
        new BungeeConnector().run();
    }
}