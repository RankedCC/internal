package cc.ranked.internal.protocol.pipeline;

import cc.ranked.internal.protocol.packet.Packet;
import cc.ranked.internal.protocol.packet.Protocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToMessageDecoder;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@EqualsAndHashCode(callSuper = true)
@Data
public class PacketDecoder extends ByteToMessageDecoder {
    Protocol proto = Protocol.PLAY;
    boolean server;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, @NotNull List<Object> out) {
        val directionData = server ? proto.TO_SERVER : proto.TO_CLIENT;
        val packetId = Packet.readVarInt(in);
        val packet = directionData.newInstance(packetId);
        packet.read(in);

        out.add(packet);
    }
}
