package com.bigbrotherlee.jaria2.client;

import com.bigbrotherlee.jaria2.client.action.Action;
import com.bigbrotherlee.jaria2.client.notification.Event;

public interface Aria2Client {
    String getToken();

    <R extends Action.ActionResponse,T extends Action<R>> R action(T action);

    void handleEvent(Event event);
}
