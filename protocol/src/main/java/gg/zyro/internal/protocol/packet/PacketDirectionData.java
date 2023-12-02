package gg.zyro.internal.protocol.packet;

import it.unimi.dsi.fastutil.objects.Object2IntArrayMap;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class PacketDirectionData {
    int MAX_PACKET_ID = 0xFF;

    @SuppressWarnings("unchecked")
    Supplier<? extends Packet>[] constructors = new Supplier[MAX_PACKET_ID];
    Object2IntArrayMap<Class<? extends Packet>> packets = new Object2IntArrayMap<>(MAX_PACKET_ID);

    public void registerPacket(int id, Class<? extends Packet<?>> packetClass, Supplier<? extends Packet<?>> constructor) {
        constructors[id] = constructor;
        packets.put(packetClass, id);
    }

    public Packet<?> newInstance(int id) {
        return constructors[id].get();
    }

    public int getId(@NotNull Packet<?> packet) {
        return packets.getInt(packet.getClass());
    }

}
