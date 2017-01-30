package com.fbinvestments;

/**
 * Created by Alexa on 26/01/2017.
 */
public class Message {
    public String exten;
    public String url;
    public String number;

    public Message(String extension, String url, String number) {
        this.exten = extension;
        this.url = url;
        this.number = number;
    }
}
