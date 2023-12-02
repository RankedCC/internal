package gg.zyro.internal.protocol.server;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Accessors(fluent = true)
@Data
public class ClientData {
    UUID uuid;
}
