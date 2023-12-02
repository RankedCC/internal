package gg.zyro.internal.protocol.packet.processing;

import gg.zyro.internal.protocol.packet.Packet;
import io.netty.channel.ChannelHandlerContext;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Data
public class ConnectionProcessorContext implements PacketProcessor {

    Packet packet; // we dont know which packetprocessor its gonna use
    MasterProcessor masterProcessor;

    ChannelHandlerContext ctx;

    @SuppressWarnings("unchecked")
    public void callProcess(PacketProcessor processor) {
        packet.process(processor);
    }

}
