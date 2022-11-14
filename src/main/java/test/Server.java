package test;

import communication.*;

public class Server {

    public static void main(String[] args) {
        SocketServer server = new SocketServer();
        server.startListening();
    }

}