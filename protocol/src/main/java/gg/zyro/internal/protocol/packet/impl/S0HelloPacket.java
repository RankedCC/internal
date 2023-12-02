package gg.zyro.internal.protocol.packet.impl;

import gg.zyro.internal.protocol.packet.Packet;
import gg.zyro.internal.protocol.packet.processing.ClientPacketProcessor;
import gg.zyro.internal.protocol.packet.processing.PacketProcessor;
import gg.zyro.internal.protocol.server.ClientData;
import io.netty.buffer.ByteBuf;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * Sent to the client to assign a UUID n shit
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = true)
@Accessors(fluent = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class S0HelloPacket extends Packet<ClientPacketProcessor> {
    ClientData data;

    @Override
    public void read(ByteBuf buf) {
        data = new ClientData(UUID.fromString(readString(buf)));
    }

    @Override
    public void write(@NotNull ByteBuf buf) {
        writeString(buf, data.uuid().toString());
    }

    @Override
    public void process(@NotNull ClientPacketProcessor processor) {
        processor.process(this);
    }

    @Override
    public boolean isProcessor(PacketProcessor processor) {
        return processor instanceof ClientPacketProcessor;
    }
}
