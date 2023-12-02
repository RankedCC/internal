package gg.zyro.internal.protocol.packet.processing;

import gg.zyro.internal.protocol.packet.impl.S0HelloPacket;

// Child processor of MasterProcessor
public interface ClientPacketProcessor extends PacketProcessor {
    default void process(final S0HelloPacket packet) {

    }
}
