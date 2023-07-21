package cn.lishiyuan.jaria2.client.handler;

import io.netty.channel.*;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketClientProtocolHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This Handler is used to handle the websocket handshake. handshake success will remove this handle from pipeline
 */
public class Aria2HandshakeHandler extends ChannelDuplexHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(Aria2HandshakeHandler.class);

    private ChannelPromise handshake;

    public ChannelPromise getHandshake() {
        return handshake;
    }


    @Override
    public void handlerAdded(ChannelHandlerContext ctx)  {
        handshake = ctx.newPromise();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if(evt instanceof WebSocketClientProtocolHandler.ClientHandshakeStateEvent){
            if(evt == WebSocketClientProtocolHandler.ClientHandshakeStateEvent.HANDSHAKE_COMPLETE){
                LOGGER.info("websocket handshake success");
                handshake.setSuccess();
                ctx.pipeline().remove(this);
            }else if (evt == WebSocketClientProtocolHandler.ClientHandshakeStateEvent.HANDSHAKE_TIMEOUT){
                LOGGER.info("websocket handshake timeout");
                handshake.setFailure(new Exception("websocket handshake timeout"));
            }else if (evt == WebSocketClientProtocolHandler.ClientHandshakeStateEvent.HANDSHAKE_ISSUED) {
                LOGGER.info("websocket handshake issued");
            }
            return;
        }

        super.userEventTriggered(ctx, evt);
    }

}
