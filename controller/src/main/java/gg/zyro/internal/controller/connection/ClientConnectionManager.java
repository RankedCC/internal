package gg.zyro.internal.controller.connection;

import gg.zyro.internal.controller.client.ConnectedClient;
import gg.zyro.internal.protocol.packet.impl.S0HelloPacket;
import gg.zyro.internal.protocol.server.ClientData;
import gg.zyro.internal.protocol.util.ClientType;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class ClientConnectionManager {
    private final ObjectList<ConnectedClient> clients = new ObjectArrayList<>();
    public static ClientConnectionManager INSTANCE;

    public ClientConnectionManager() {
        init();
    }

    private void init() {
        INSTANCE = this;
    }

    public void onClientConnected(final @NotNull RemoteClientConnection connection) {
        val data = new ClientData(UUID.randomUUID());
        connection.sendPacket(new S0HelloPacket(data));
        clients.add(new ConnectedClient(ClientType.UNDEFINED, data, connection));

        System.out.println("Client connected: " + data.uuid() + " : " + connection.channel().remoteAddress());
    }

    public void switchType(final UUID uuid, final ClientType type) {
        val client = getClient(uuid);
        if (client == null) return;

        clients.remove(client);
        clients.add(new ConnectedClient(type, client.data(), client.connection()));
    }

    private @Nullable ConnectedClient getClient(final UUID uuid) {
        for (val client : clients) {
            if (!client.data().uuid().equals(uuid)) continue;

            return client;
        }

        return null;
    }
}

