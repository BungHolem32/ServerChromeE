package com.fbinvestments;

import com.fbinvestments.messageObserver.Observer;
import com.fbinvestments.messageObserver.Subject;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Alexa on 26/01/2017.
 */
public class ReceivingServer extends WebSocketServer implements Subject {
    private List<Observer> observers = new ArrayList<Observer>();

    public ReceivingServer(int port) throws UnknownHostException {
        super( new InetSocketAddress(port));
    }

    public ReceivingServer(InetSocketAddress address) {
        super(address);
    }

    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
        System.out.println( webSocket.getRemoteSocketAddress().getAddress().getHostAddress() + " connected!" );
    }

    @Override
    public void onClose(WebSocket webSocket, int i, String s, boolean b) {
        System.out.println( webSocket + " disconnected!" );

    }

    @Override
    public void onMessage(WebSocket webSocket, String message) {
        notifyAllObservers(message);
        System.out.println( webSocket + ": " + message );
        // TODO Validate message and send it to BroadcastingServer
    }

    @Override
    public void onError(WebSocket webSocket, Exception e) {
        e.printStackTrace();
        if( webSocket != null ) {
            // some errors like port binding failed may not be assignable to a specific websocket
        }
    }

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifyAllObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}
