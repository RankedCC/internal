package gg.zyro.internal.protocol.packet.impl;

import gg.zyro.internal.protocol.packet.Packet;
import gg.zyro.internal.protocol.packet.processing.PacketProcessor;
import gg.zyro.internal.protocol.packet.processing.ServerPacketProcessor;
import gg.zyro.internal.protocol.util.ClientType;
import io.netty.buffer.ByteBuf;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = true)
@Accessors(fluent = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class C1ClientDataPacket extends Packet<ServerPacketProcessor> {
    ClientType clientType; // TODO: Add request/response logic and handle this properly ffs
    UUID clientUUID;

    @Override
    public void read(@NotNull ByteBuf buf) {
        clientType = readEnum(buf, ClientType.class);
        clientUUID = UUID.fromString(readString(buf));
    }

    @Override
    public void write(@NotNull ByteBuf buf) {
        writeEnum(buf, clientType);
        writeString(buf, clientUUID.toString());
    }

    @Override
    public void process(@NotNull ServerPacketProcessor processor) {
        processor.process(this);
    }

    @Override
    public boolean isProcessor(PacketProcessor processor) {
        return processor instanceof ServerPacketProcessor;
    }
}
