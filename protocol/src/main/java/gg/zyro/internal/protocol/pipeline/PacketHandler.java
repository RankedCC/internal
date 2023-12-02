package gg.zyro.internal.protocol.pipeline;

import gg.zyro.internal.protocol.packet.Packet;
import gg.zyro.internal.protocol.packet.processing.ConnectionProcessorContext;
import gg.zyro.internal.protocol.packet.processing.MasterProcessor;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.jetbrains.annotations.NotNull;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class PacketHandler extends SimpleChannelInboundHandler<Packet<?>> {
    MasterProcessor processor;

    @Override
    public void channelActive(@NotNull ChannelHandlerContext ctx) throws Exception {
        processor.callActive();
    }

    @Override
    public void channelInactive(@NotNull ChannelHandlerContext ctx) throws Exception {
        processor.callInactive();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, @NotNull Packet packet) {
        // Creating a new ProcessorContext and passing the shit to it
        val connectionProcessorContext = new ConnectionProcessorContext(packet, processor, ctx);
        try {
            processor.callProcessor(connectionProcessorContext);
        } catch (Throwable t) {
            System.out.println("fucked up on packet processing: " + t.getMessage()); // TODO: Logging
        }
    }
}
