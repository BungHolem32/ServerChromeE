package com.fbinvestments;

/**
 * Created by Alexa on 26/01/2017.
 */
public class Message {
    public String extension;
    public String url;
    public String phone;

    public Message(String extension, String url, String number) {
        this.extension = extension;
        this.url = url;
        this.phone = number;
    }
}
