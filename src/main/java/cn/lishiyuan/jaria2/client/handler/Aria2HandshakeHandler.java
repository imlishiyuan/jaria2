package cn.lishiyuan.jaria2.client.handler;

import io.netty.channel.*;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketClientProtocolHandler;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This Handler is used to handle the websocket handshake. handshake success will remove this handle from pipeline
 */
@Slf4j
public class Aria2HandshakeHandler extends ChannelDuplexHandler {

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
                log.info("websocket handshake success");
                handshake.setSuccess();
                ctx.pipeline().remove(this);
            }else if (evt == WebSocketClientProtocolHandler.ClientHandshakeStateEvent.HANDSHAKE_TIMEOUT){
                log.info("websocket handshake timeout");
                handshake.setFailure(new Exception("websocket handshake timeout"));
            }else if (evt == WebSocketClientProtocolHandler.ClientHandshakeStateEvent.HANDSHAKE_ISSUED) {
                log.info("websocket handshake issued");
            }
            return;
        }

        super.userEventTriggered(ctx, evt);
    }

}
