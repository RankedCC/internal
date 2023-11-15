package cc.ranked.internal.protocol.pipeline;

import cc.ranked.internal.protocol.packet.Packet;
import cc.ranked.internal.protocol.packet.Protocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
public class PacketEncoder extends MessageToByteEncoder<Packet<?>> {
    boolean server;
    Protocol proto = Protocol.PLAY; // TODO: maybe change

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet packet, ByteBuf out) {
        val directionData = server ? proto.TO_CLIENT : proto.TO_SERVER;
        Packet.writeVarInt(out, directionData.getId(packet));
        packet.write(out);
    }
}
