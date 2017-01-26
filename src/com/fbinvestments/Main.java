package com.fbinvestments;

import org.java_websocket.WebSocketImpl;

public class Main {
    public static void main(String[] args) {
        WebSocketImpl.DEBUG = true;
        int broadcastingPort = 8886; // 843 flash policy port
        int receivingPort = 8887; // 843 flash policy port
        try {
            if (args.length > 0) {
                broadcastingPort = Integer.parseInt( args[ 0 ] );
            }
            if (args.length > 1) {
                receivingPort = Integer.parseInt( args[ 1 ] );
            }
            ReceivingServer receivingServer = new ReceivingServer(receivingPort);
            BroadcastingServer broadcastingServer = new BroadcastingServer( broadcastingPort );
            MessageFilter messageFilter = new MessageFilter();
            receivingServer.attach(messageFilter);
            messageFilter.attach(broadcastingServer);
            receivingServer.start();
            System.out.println( "ReceivingServer started on port: " + receivingServer.getPort() );
            broadcastingServer.start();
            System.out.println( "BroadcastingServer started on port: " + broadcastingServer.getPort() );
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }
}
