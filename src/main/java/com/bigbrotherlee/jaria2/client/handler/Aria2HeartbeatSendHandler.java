package com.bigbrotherlee.jaria2.client.handler;

import com.bigbrotherlee.jaria2.client.action.ListNotificationAction;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * This Handler is used to handle the heartbeat. ListNotificationAction will be the heartbeat content
 * @author lee
 */
@ChannelHandler.Sharable
public class Aria2HeartbeatSendHandler extends ChannelDuplexHandler {

    public static final String DEFAULT_ACTION_ID = "heartbeat";

    private static final Logger LOGGER = LoggerFactory.getLogger(Aria2HeartbeatSendHandler.class);

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) {
        if(evt == IdleStateEvent.FIRST_WRITER_IDLE_STATE_EVENT && ctx.channel().isActive()){
            // send heartbeat
            ctx.writeAndFlush(new ListNotificationAction(DEFAULT_ACTION_ID));
            LOGGER.debug("send heartbeat message");
        }
    }

}
