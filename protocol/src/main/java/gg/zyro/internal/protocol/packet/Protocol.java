package gg.zyro.internal.protocol.packet;

import gg.zyro.internal.protocol.packet.impl.C1ClientDataPacket;
import gg.zyro.internal.protocol.packet.impl.C2TestPacket;
import gg.zyro.internal.protocol.packet.impl.S0HelloPacket;

import java.util.function.Supplier;

public enum Protocol {

    PLAY {
        {
            toClient(0, S0HelloPacket.class, S0HelloPacket::new);
            toServer(1, C1ClientDataPacket.class, C1ClientDataPacket::new);
            toServer(2, C2TestPacket.class, C2TestPacket::new);
        }
    };

    public void toServer(final int id, Class<? extends Packet<?>> cls, Supplier<? extends Packet<?>> supplier) {
        TO_SERVER.registerPacket(id, cls, supplier);
    }

    public void toClient(final int id, Class<? extends Packet<?>> cls, Supplier<? extends Packet<?>> supplier) {
        TO_CLIENT.registerPacket(id, cls, supplier);
    }

    public final PacketDirectionData
            TO_SERVER = new PacketDirectionData(),
            TO_CLIENT = new PacketDirectionData();

}
