package gg.zyro.internal.controller;

import gg.zyro.internal.controller.connection.ClientConnectionManager;
import gg.zyro.internal.controller.connection.LocalServerConnection;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.val;

import java.net.InetSocketAddress;

@Accessors(fluent = true)
@Getter
public class Controller {
    final ClientConnectionManager connectionManager;
    public static Controller INSTANCE;

    public Controller() {
        connectionManager = new ClientConnectionManager();
    }

    public void run() throws Exception {
        INSTANCE = this;
        val localConnection = new LocalServerConnection(new InetSocketAddress(1338));
        localConnection.bindSynchronized();
    }


    public static void main(String[] args) throws Exception {
        new Controller().run();
    }
}