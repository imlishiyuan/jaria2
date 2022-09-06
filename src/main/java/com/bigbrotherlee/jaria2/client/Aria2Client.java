package com.bigbrotherlee.jaria2.client;

import com.bigbrotherlee.jaria2.client.action.Action;
import com.bigbrotherlee.jaria2.client.notification.Event;

public interface Aria2Client {

    /**
     * 连接服务
     */
    void connect() throws InterruptedException;

    /**
     * 断开连接
     */
    void disconnect() throws InterruptedException;

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

    public static enum ConnectStatus{
        /**
         * 初始化完成，已经准备好连接但是还未连接
         * 调用构造器 | 调用disconnect
         */
        READY(0,"就绪"),

        /**
         * 连接成功，调用connect成功之后
         */
        CONNECTED(1,"已连接")
        ,;


        private final int code;
        private final String description;

        private ConnectStatus(int code,String description){
            this.code = code;
            this.description = description;
        }
    }
}
