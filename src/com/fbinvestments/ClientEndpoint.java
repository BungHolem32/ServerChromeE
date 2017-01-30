package com.fbinvestments;

import javax.websocket.*;

@javax.websocket.ClientEndpoint
public class ClientEndpoint {

    private static String lastMessage = "";

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Connected to endpoint: " + session.getBasicRemote());
    }

    @OnMessage
    public void processMessage(String message) {
        ClientEndpoint.lastMessage = message;
    }

    @OnError
    public void processError(Throwable t) {
        t.printStackTrace();
    }

    public static String getLastMessage() {
        return ClientEndpoint.lastMessage;
    }
}
