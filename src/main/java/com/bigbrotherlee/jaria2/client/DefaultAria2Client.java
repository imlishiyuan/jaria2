package com.bigbrotherlee.jaria2.client;

import com.bigbrotherlee.jaria2.client.action.Action;
import com.bigbrotherlee.jaria2.client.notification.Event;

public class DefaultAria2Client implements Aria2Client{
    @Override
    public String getToken() {
        return null;
    }

    @Override
    public <R extends Action.ActionResponse,T extends Action<R>> R action(T action) {
        return null;
    }

    @Override
    public void handleEvent(Event event) {

    }
}
