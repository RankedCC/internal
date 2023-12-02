package gg.zyro.internal.protocol.packet.impl;

import gg.zyro.internal.protocol.packet.Packet;
import gg.zyro.internal.protocol.packet.processing.PacketProcessor;
import gg.zyro.internal.protocol.packet.processing.ServerPacketProcessor;
import io.netty.buffer.ByteBuf;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class C2TestPacket extends Packet<ServerPacketProcessor> {
    String message;

    @Override
    public void read(ByteBuf buf) {
        message = readString(buf);
    }

    @Override
    public void write(ByteBuf buf) {
        writeString(buf, message);
    }

    @Override
    public void process(@NotNull ServerPacketProcessor processor) {
        processor.process(this); // here we get to the correct and final (in the protocol) processor
    }

    @Override
    public boolean isProcessor(PacketProcessor processor) {
        return processor instanceof ServerPacketProcessor;
    }

}
