package cc.ranked.internal.controller;

import cc.ranked.internal.controller.connection.LocalServerConnection;
import lombok.val;

import java.net.InetSocketAddress;

public class Controller {

    public void run() throws Exception {
        val localConnection = new LocalServerConnection(new InetSocketAddress(1337));
        localConnection.bindSynchronized();
    }


    public static void main(String[] args) throws Exception {
        new Controller().run();
    }
}