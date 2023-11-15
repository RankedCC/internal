package cc.ranked.internal.protocol.packet;

import cc.ranked.internal.protocol.packet.impl.C0TestPacket;

public enum Protocol {

    PLAY {
        {
            TO_SERVER.registerPacket(0, C0TestPacket.class, C0TestPacket::new);
        }
    };

    public final PacketDirectionData
            TO_SERVER = new PacketDirectionData(),
            TO_CLIENT = new PacketDirectionData();

}
