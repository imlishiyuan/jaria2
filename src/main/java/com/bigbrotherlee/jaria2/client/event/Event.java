package com.bigbrotherlee.jaria2.client.event;

public abstract class Event {
    private final String eventName;
    protected Event(String eventName){
        this.eventName = eventName;
    }

    public String getEventName() {
        return eventName;
    }
}
