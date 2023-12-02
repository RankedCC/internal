package gg.zyro.internal.controller.client;

import gg.zyro.internal.controller.connection.RemoteClientConnection;
import gg.zyro.internal.protocol.server.ClientData;
import gg.zyro.internal.protocol.util.ClientType;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Accessors(fluent = true)
@Data
public class ConnectedClient {
    ClientType type;
    ClientData data;
    RemoteClientConnection connection;
}
