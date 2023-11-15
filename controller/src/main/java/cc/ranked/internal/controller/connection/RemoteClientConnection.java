package cc.ranked.internal.controller.connection;

import cc.ranked.internal.protocol.connection.AbstractRemoteClientConnection;
import cc.ranked.internal.protocol.packet.impl.C0TestPacket;
import io.netty.channel.socket.SocketChannel;

public class RemoteClientConnection extends AbstractRemoteClientConnection {
    public RemoteClientConnection(SocketChannel channel) {
        super(channel);
    }

    @Override
    public void process(C0TestPacket packet) {
        System.out.println("Received C0TestPacket, yay!");
    }

    @Override
    protected void onDisconnect() {
        System.out.println("disconnected");
    }
}
