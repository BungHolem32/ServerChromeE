package com.fbinvestments.messageObserver;

/**
 * Created by Alexa on 26/01/2017.
 */
public interface Subject {
    public void attach(Observer observer);
    public void notifyAllObservers(String message);
}
