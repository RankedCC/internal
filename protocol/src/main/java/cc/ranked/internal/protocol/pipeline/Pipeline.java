package cc.ranked.internal.protocol.pipeline;

import cc.ranked.internal.protocol.connection.Connection;
import io.netty.channel.socket.SocketChannel;
import lombok.val;
import org.jetbrains.annotations.NotNull;

public class Pipeline {

    public Pipeline(@NotNull Connection connection, @NotNull SocketChannel socketChannel, boolean server) {
        val pipeline = socketChannel.pipeline();

        pipeline
                .addLast(new PacketEncoder(server))
                .addLast(new PacketDecoder(server))
                .addLast(new PacketHandler(connection.newMasterProcessor(socketChannel)));
    }

}
