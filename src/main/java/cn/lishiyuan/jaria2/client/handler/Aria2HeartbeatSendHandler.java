package cn.lishiyuan.jaria2.client.handler;

import cn.lishiyuan.jaria2.client.action.ListNotificationAction;
import cn.lishiyuan.jaria2.config.Aria2Config;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;


/**
 * This Handler is used to handle the heartbeat. ListNotificationAction will be the heartbeat content
 * @author lee
 */
@ChannelHandler.Sharable
@Slf4j
public class Aria2HeartbeatSendHandler extends ChannelDuplexHandler {
    public static final String HEARTBEAT_ID = "heartbeat";

    private final AtomicInteger HEARTBEAT_COUNT = new AtomicInteger(0);

    private final int MAX_HEARTBEAT_COUNT;

    public Aria2HeartbeatSendHandler(int maxHeartbeatCount){
        this.MAX_HEARTBEAT_COUNT = maxHeartbeatCount;
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) {
        if(evt == IdleStateEvent.WRITER_IDLE_STATE_EVENT && ctx.channel().isActive()){
            if(HEARTBEAT_COUNT.incrementAndGet() > MAX_HEARTBEAT_COUNT){
                if (ctx.channel().hasAttr(Aria2Config.Client.ARIA2_CLIENT_ATTRIBUTE_KEY)) {
                    try {
                        ctx.channel().attr(Aria2Config.Client.ARIA2_CLIENT_ATTRIBUTE_KEY).get().disconnect();
                    } catch (InterruptedException e) {
                        log.error("close channel error", e);
                    }
                }
                HEARTBEAT_COUNT.set(0);
                return;
            }
            // send heartbeat
            ctx.writeAndFlush(new ListNotificationAction(HEARTBEAT_ID));
            log.debug("send heartbeat message");
        }
    }

    public void receiveHeartbeat(){
        HEARTBEAT_COUNT.set(0);
    }

}
