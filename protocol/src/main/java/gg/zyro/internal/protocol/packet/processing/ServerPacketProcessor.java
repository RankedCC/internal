package gg.zyro.internal.protocol.packet.processing;

import gg.zyro.internal.protocol.packet.impl.C1ClientDataPacket;
import gg.zyro.internal.protocol.packet.impl.C2TestPacket;

// Goes after the MasterProcessor in the processing pipeline
public interface ServerPacketProcessor extends PacketProcessor {
    // Override
    default void process(final C1ClientDataPacket packet) {

    }

    default void process(final C2TestPacket packet) {

    }
}
