package com.bigbrotherlee.jaria2.client;

import com.bigbrotherlee.jaria2.client.action.Action;
import com.bigbrotherlee.jaria2.client.notification.Event;

import java.util.concurrent.atomic.AtomicInteger;

public class DefaultAria2Client implements Aria2Client{

    private final AtomicInteger state;

    public DefaultAria2Client(){
        // TODO 处理初始化

        state = new AtomicInteger(READY);
    }


    @Override
    public void connect() {
        if(!state.compareAndSet(READY,CONNECTED))
            throw new IllegalStateException("client not ready");
        // TODO 连接操作


    }

    @Override
    public void disconnect() {
        if(!state.compareAndSet(CONNECTED,READY))
            throw new IllegalStateException("client not connected");
        // 断开连接操作
    }

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
