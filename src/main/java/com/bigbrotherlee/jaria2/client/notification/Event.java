package com.bigbrotherlee.jaria2.client.notification;

public abstract class Event {
    private String eventName;
    protected Event(String eventName){
        this.eventName = eventName;
    }
}
