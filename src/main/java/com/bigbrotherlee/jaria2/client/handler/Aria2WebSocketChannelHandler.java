package com.bigbrotherlee.jaria2.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.websocketx.*;

import java.net.URI;

public class Aria2WebSocketChannelHandler extends SimpleChannelInboundHandler<Object> {
    private final WebSocketClientHandshaker webSocketClientHandshaker;

    public Aria2WebSocketChannelHandler(URI uri){
        webSocketClientHandshaker = new WebSocketClientHandshaker13(uri, WebSocketVersion.V13,null,true,new DefaultHttpHeaders(),8192);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        webSocketClientHandshaker.handshake(ctx.channel(),ctx.newPromise());
        super.channelActive(ctx);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(!webSocketClientHandshaker.isHandshakeComplete())
            return;
        // 处理数据
        if(msg instanceof TextWebSocketFrame){
            TextWebSocketFrame textWebSocketFrame = (TextWebSocketFrame) msg;
            String text = textWebSocketFrame.text();
            System.out.println(text);
        }else if(msg instanceof CloseWebSocketFrame){
            webSocketClientHandshaker.close(ctx, ((CloseWebSocketFrame) msg));
            // TODO 更改client
        }
    }

    public static Aria2WebSocketChannelHandler newInstance(URI uri) {
        return new Aria2WebSocketChannelHandler(uri);
    }


}
