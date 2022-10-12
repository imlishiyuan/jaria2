package com.bigbrotherlee.jaria2.client.handler;

import com.bigbrotherlee.jaria2.client.action.Action;
import com.bigbrotherlee.jaria2.exception.Aria2ActionException;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * This Handler is used to handle the action. Convert Action to TextWebSocketFrame
 *  @author lee
 */
public class Aria2ActionSendHandler extends MessageToMessageEncoder<Action> {

    private static final Logger LOGGER = LoggerFactory.getLogger(Aria2ActionSendHandler.class);

    @Override
    protected void encode(ChannelHandlerContext ctx, Action msg, List<Object> out) throws Exception {
        String actionStr = msg.toJsonString();
        if(StringUtils.isBlank(actionStr)){
            LOGGER.error("the json string of action must not blank. action id is " + msg.getId());
            throw new Aria2ActionException("the json string of action must not blank. action id is " + msg.getId());
        }
        out.add(new TextWebSocketFrame(actionStr));
        LOGGER.info("send action : "+msg.getMethod()+", id: "+msg.getId());
    }

    public static Aria2ActionSendHandler newInstance() {
        return new Aria2ActionSendHandler();
    }

}
