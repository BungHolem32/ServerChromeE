package com.fbinvestments;

import com.fbinvestments.messageObserver.Observer;
import com.fbinvestments.messageObserver.Subject;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexa on 26/01/2017.
 */
public class MessageFilter implements Observer, Subject {
    private List<Observer> observers = new ArrayList<Observer>();

    public void attach(Observer observer){
        observers.add(observer);
    }

    public void notifyAllObservers(String message){
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    @Override
    public void update(String message) {
        //System.out.println(message);
        Gson gson = new Gson();
        Message fromJson = null;
        try {
            fromJson = gson.fromJson(message, Message.class);
        } catch (Exception e) {
            System.out.println("Error during parsing Json: " + e.getLocalizedMessage());
            return;
        }
        if (fromJson.exten == null || fromJson.url == null || fromJson.number == null) {
            System.out.println("Error during parsing Json");
            return;
        }
        this.notifyAllObservers(gson.toJson(fromJson));
    }
}
