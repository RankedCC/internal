package cc.ranked.internal.protocol.packet.processing;

import cc.ranked.internal.protocol.packet.impl.C0TestPacket;

// Child processor of MasterProcessor
public interface ServerPacketProcessor extends PacketProcessor {
    // Override
    default void process(final C0TestPacket packet) {

    }
}
