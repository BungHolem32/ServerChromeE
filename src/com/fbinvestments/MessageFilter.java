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
        Message fromJson = gson.fromJson(message, Message.class);
        if (fromJson.extension == null || fromJson.url == null || fromJson.phone == null) {
            return;
        }
        this.notifyAllObservers(gson.toJson(fromJson));
    }
}
