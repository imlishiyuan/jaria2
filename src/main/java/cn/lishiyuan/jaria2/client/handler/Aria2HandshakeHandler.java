package cn.lishiyuan.jaria2.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This Handler is used to handle the websocket handshake. handshake success will remove this handle from pipeline
 */
public class Aria2HandshakeHandler extends SimpleChannelInboundHandler<FullHttpResponse> {

    private static final Logger LOGGER = LoggerFactory.getLogger(Aria2HandshakeHandler.class);

    private final WebSocketClientHandshaker webSocketClientHandshaker;

    private ChannelPromise handshake;

    public ChannelPromise getHandshake() {
        return handshake;
    }

    public Aria2HandshakeHandler(WebSocketClientHandshaker webSocketClientHandshaker) {
        this.webSocketClientHandshaker = webSocketClientHandshaker;
    }


    @Override
    public void handlerAdded(ChannelHandlerContext ctx)  {
        handshake = ctx.newPromise();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx){
        webSocketClientHandshaker.handshake(ctx.channel());
        LOGGER.debug("start aria2 websocket Handshake");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpResponse msg) {
        /*
         * if handshake complete ，than set to success for handshake.  this handler will remove from pipeline at final.
         * else finish handshake first.
         */

        if(!webSocketClientHandshaker.isHandshakeComplete()){
            webSocketClientHandshaker.finishHandshake(ctx.channel(),msg);
            LOGGER.debug("finishHandshake");
            handshake.setSuccess();
        }

        ctx.pipeline().remove(this);
        LOGGER.info("aria2 websocket Handshake is finished");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        handshake.setFailure(cause);
        ctx.pipeline().remove(this);
        LOGGER.info("aria2 websocket Handshake is Failure");
        super.exceptionCaught(ctx, cause);
    }
}
