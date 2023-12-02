package gg.zyro.internal.protocol.packet.processing;

import org.jetbrains.annotations.NotNull;

public interface PacketProcessor {

    // implement
    default void process(final @NotNull Throwable throwable) {
        throwable.printStackTrace();
    }

    default void process(@NotNull ConnectionProcessorContext ctx) {
        ctx.callProcess(this);
    }

    /**
     * Process connect
     */
    default void active() throws Exception {
        // for implementation
    }

    /**
     * Process disconnect
     */
    default void inactive() throws Exception {
        // for implementation
    }

}
