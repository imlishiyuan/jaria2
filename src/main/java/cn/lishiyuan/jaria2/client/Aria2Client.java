package cn.lishiyuan.jaria2.client;

import cn.lishiyuan.jaria2.client.action.Action;
import cn.lishiyuan.jaria2.client.action.ActionResponse;
import cn.lishiyuan.jaria2.client.event.process.EventProcessor;
import cn.lishiyuan.jaria2.exception.Aria2ActionException;

import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

public interface Aria2Client {

    Map<String, CompletableFuture<String>> CACHE = new WeakHashMap<>(128);

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

    void addEventProcessor(EventProcessor eventProcessor);

    void addEventProcessors(EventProcessor... eventProcessor);

    /**
     * 调用aria2c
     * @param action
     * @return
     * @param <R>
     * @param <T>
     */
    <R extends ActionResponse,T extends Action<R>> R action(T action) throws Aria2ActionException;
    enum ConnectStatus{
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
