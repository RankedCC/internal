package cc.ranked.internal.connector.bungee;

import cc.ranked.internal.connector.bungee.connection.ConnectorLocalServerConnection;
import cc.ranked.internal.protocol.packet.impl.C0TestPacket;
import lombok.val;

import java.net.InetSocketAddress;

public class BungeeConnector {
    public void run() throws Exception {
        val localConnection = new ConnectorLocalServerConnection(new InetSocketAddress(
                "localhost",
                1337
        ));

        localConnection.connectSynchronized();
        localConnection.sendPacket(new C0TestPacket());
    }

    public static void main(String[] args) throws Exception {
        new BungeeConnector().run();
    }
}