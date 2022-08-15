package com.bigbrotherlee.jaria2;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.websocketx.extensions.WebSocketClientExtension;
import io.netty.handler.codec.http.websocketx.extensions.WebSocketClientExtensionHandler;
import io.netty.handler.codec.http.websocketx.extensions.WebSocketClientExtensionHandshaker;
import io.netty.handler.codec.http.websocketx.extensions.WebSocketExtensionData;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class WebsocketTest {
    @Test
    public void connect(){
        String address = "localhost";
        int port = 6800;
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap(); // (1)
            b.group(workerGroup); // (2)
            b.channel(NioSocketChannel.class); // (3)
            b.option(ChannelOption.SO_KEEPALIVE, true); // (4)
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new WebSocketClientExtensionHandler(new MyWebSocketClientExtensionHandshaker()));
                }
            });

            // Start the client.
            ChannelFuture future = b.connect(address, port).sync(); // (5)

            // Wait until the connection is closed.
            future.channel().closeFuture().sync();
        } catch (Exception e){

        }finally {
            workerGroup.shutdownGracefully();
        }

    }

    public static class MyWebSocketClientExtensionHandshaker implements WebSocketClientExtensionHandshaker{
        @Override
        public WebSocketExtensionData newRequestData() {
            return new WebSocketExtensionData("aria2.pauseAll",new HashMap<>());
        }

        @Override
        public WebSocketClientExtension handshakeExtension(WebSocketExtensionData extensionData) {
            System.out.println("===");
            return null;
        }
    }
}
