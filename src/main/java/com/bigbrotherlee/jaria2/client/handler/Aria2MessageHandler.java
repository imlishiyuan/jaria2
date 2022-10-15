package com.bigbrotherlee.jaria2.client.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bigbrotherlee.jaria2.Aria2Manager;
import com.bigbrotherlee.jaria2.client.Aria2Client;
import com.bigbrotherlee.jaria2.client.enums.EventEnum;
import com.bigbrotherlee.jaria2.client.event.Event;
import com.bigbrotherlee.jaria2.client.event.process.EventProcessor;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.http.websocketx.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;

public class Aria2MessageHandler extends MessageToMessageDecoder<WebSocketFrame> {

    private List<EventProcessor> eventProcessors = new CopyOnWriteArrayList<>();

    private static final String ID_KEY = "id";

    private static final String METHOD_KEY = "method";

    private static final Logger LOGGER = LoggerFactory.getLogger(Aria2MessageHandler.class);

    public void addEventProcessor(EventProcessor eventProcessor){
        this.eventProcessors.add(eventProcessor);
    }

    public void addEventProcessors(EventProcessor... eventProcessor){
        addEventProcessors(Arrays.asList(eventProcessor));
    }

    public void addEventProcessors(Collection<EventProcessor> eventProcessors){
        this.eventProcessors.addAll(eventProcessors);
    }

    public List<EventProcessor> getEventProcessors(){
        return eventProcessors;
    }


    @Override
    protected void decode(ChannelHandlerContext ctx, WebSocketFrame webSocketFrame, List<Object> out) throws Exception {
        // 处理数据
        if(webSocketFrame instanceof TextWebSocketFrame){
            TextWebSocketFrame textWebSocketFrame = (TextWebSocketFrame) webSocketFrame;
            String text = textWebSocketFrame.text();
            // 封发通知与Action回复
            LOGGER.debug("receive msg: "+text);
            JSONObject respJson = JSON.parseObject(text);
            String id = respJson.getString(ID_KEY);
            if(StringUtils.isBlank(id)){
                // 通知没有ID
                String method = respJson.getString(METHOD_KEY);
                EventEnum eventEnum = EventEnum.parseByName(method);
                Event event = (Event) JSON.parseObject(text, eventEnum.eventClass);
                for (EventProcessor eventProcessor : eventProcessors) {
                    eventProcessor.process(event);
                }
                return;
            }
            // 带有标识的才是我们发的msg
            CompletableFuture<String> stringCompletableFuture = Aria2Client.CACHE.get(id);
            if(Objects.nonNull(stringCompletableFuture)){
                stringCompletableFuture.complete(text);
            }
        }else if(webSocketFrame instanceof CloseWebSocketFrame){
            ctx.close();
        }
    }


    public static Aria2MessageHandler newInstance() {
        return new Aria2MessageHandler();
    }


}
