package cc.ranked.internal.protocol.packet.impl;

import cc.ranked.internal.protocol.packet.Packet;
import cc.ranked.internal.protocol.packet.processing.ConnectionProcessorContext;
import cc.ranked.internal.protocol.packet.processing.PacketProcessor;
import cc.ranked.internal.protocol.packet.processing.ServerPacketProcessor;
import io.netty.buffer.ByteBuf;
import org.jetbrains.annotations.NotNull;

public class C0TestPacket extends Packet<ServerPacketProcessor> {
    @Override
    public void read(ByteBuf buf) {

    }

    @Override
    public void write(ByteBuf buf) {

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
