package gg.zyro.internal.protocol.connection;

import gg.zyro.internal.protocol.packet.Packet;
import gg.zyro.internal.protocol.packet.processing.MasterProcessor;
import io.netty.channel.socket.SocketChannel;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PROTECTED, makeFinal = true)
@Accessors(fluent = true)
@RequiredArgsConstructor
@Getter
public abstract class AbstractRemoteConnection extends MasterProcessor {

    SocketChannel channel; // main comms channel with the remote


    public boolean isActive() {
        return channel.isActive();
    }

    public void close() {
        if (!isActive()) {
            return;
        }

        channel.close();
    }

    public void sendPacket(@NonNull Packet<?> packet) {
        if (!isActive()) {
            return;
        }

        channel.writeAndFlush(packet, channel.voidPromise());
    }

    public void disconnect() {
        onDisconnect();
    }

    protected abstract void onDisconnect();

}
