package com.bigbrotherlee.jaria2.client;

import com.bigbrotherlee.jaria2.client.action.Action;
import com.bigbrotherlee.jaria2.client.notification.Event;

public interface Aria2Client {
    /**
     * 初始化完成，已经准备好连接但是还未连接
     * 调用构造器 | 调用disconnect
     */
    int READY = 0;

    /**
     * 连接成功，调用connect成功之后
     */
    int CONNECTED = 1;

    /**
     * 连接服务
     */
    void connect();

    /**
     * 断开连接
     */
    void disconnect();

    /**
     * 获取taken
     * @return
     */
    String getToken();

    /**
     * 调用aria2c
     * @param action
     * @return
     * @param <R>
     * @param <T>
     */
    <R extends Action.ActionResponse,T extends Action<R>> R action(T action);

    /**
     * 处理通知
     * @param event
     */
    void handleEvent(Event event);
}
