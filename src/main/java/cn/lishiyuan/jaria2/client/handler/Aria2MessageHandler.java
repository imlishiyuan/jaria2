package cn.lishiyuan.jaria2.client.handler;

import cn.lishiyuan.jaria2.client.event.process.EventProcessor;
import cn.lishiyuan.jaria2.config.Aria2Config;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import cn.lishiyuan.jaria2.client.Aria2Client;
import cn.lishiyuan.jaria2.client.enums.EventEnum;
import cn.lishiyuan.jaria2.client.event.Event;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.http.websocketx.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;

@Slf4j
public class Aria2MessageHandler extends MessageToMessageDecoder<WebSocketFrame> {

    private final List<EventProcessor> eventProcessors = new CopyOnWriteArrayList<>();

    private static final String ID_KEY = "id";

    private static final String METHOD_KEY = "method";

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
        boolean hasAria2Client = ctx.channel().hasAttr(Aria2Config.Client.ARIA2_CLIENT_ATTRIBUTE_KEY);

        if(webSocketFrame instanceof TextWebSocketFrame textWebSocketFrame){
            String text = textWebSocketFrame.text();
            // 封发通知与Action回复
            log.debug("receive msg: "+text);
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

            if (hasAria2Client){
                // 有客户端
                Aria2Client aria2Client = ctx.channel().attr(Aria2Config.Client.ARIA2_CLIENT_ATTRIBUTE_KEY).get();
                // 带有标识的才是我们发的msg
                CompletableFuture<String> stringCompletableFuture = aria2Client.getCache().get(id);
                if(Objects.nonNull(stringCompletableFuture)){
                    stringCompletableFuture.complete(text);
                } else if (Aria2HeartbeatSendHandler.HEARTBEAT_ID.equals(id)) {
                    // 心跳
                    log.debug("receive heartbeat msg: "+text);
                    if (ctx.channel().hasAttr(Aria2Config.Client.ARIA2_HEARTBEAT_SEND_HANDLER_ATTRIBUTE_KEY)) {
                        Aria2HeartbeatSendHandler heartbeatSendHandler = ctx.channel().attr(Aria2Config.Client.ARIA2_HEARTBEAT_SEND_HANDLER_ATTRIBUTE_KEY).get();
                        heartbeatSendHandler.receiveHeartbeat();
                    }
                } else {
                    log.error("receive msg: "+text);
                }
            }
        }else if(webSocketFrame instanceof CloseWebSocketFrame){
            if (hasAria2Client){
                // 有客户端
                Aria2Client aria2Client = ctx.channel().attr(Aria2Config.Client.ARIA2_CLIENT_ATTRIBUTE_KEY).get();
                aria2Client.disconnect();
            }
            log.error("receive close message");
        }
    }




    public static Aria2MessageHandler newInstance() {
        return new Aria2MessageHandler();
    }


}
