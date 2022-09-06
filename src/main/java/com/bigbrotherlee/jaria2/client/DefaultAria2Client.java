package com.bigbrotherlee.jaria2.client;

import com.bigbrotherlee.jaria2.client.action.Action;
import com.bigbrotherlee.jaria2.client.handler.Aria2WebSocketChannelHandler;
import com.bigbrotherlee.jaria2.client.notification.Event;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.websocketx.WebSocket13FrameDecoder;
import io.netty.handler.codec.http.websocketx.WebSocket13FrameEncoder;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker13;
import io.netty.handler.codec.http.websocketx.WebSocketClientProtocolHandler;
import io.netty.handler.ssl.SslHandler;

import java.net.URI;
import java.util.concurrent.atomic.AtomicReference;

public class DefaultAria2Client implements Aria2Client{
    private final AddressPort addressPort;
    private final Connector connector;
    private final AtomicReference<ConnectStatus> state;

    private final String token;

    public DefaultAria2Client(String token){
        this("localhost",6800,token,false);
    }

    public DefaultAria2Client(String address, int port,String token,boolean useSSL){
        this.addressPort = new AddressPort(address,port,useSSL);
        EventLoopGroup workerGroup = new EpollEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap
                .group(workerGroup)
                .option(ChannelOption.SO_KEEPALIVE,true)
                .channel(EpollSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) {
                        // TODO 添加解析与处理逻辑
                        ch.pipeline()
                                .addLast(Aria2WebSocketChannelHandler.newInstance(addressPort.getUri()));
                    }
                });

        this.connector = new Connector(bootstrap,workerGroup);
        this.token = token;
        state = new AtomicReference<>(ConnectStatus.READY);
    }


    @Override
    public void connect() throws InterruptedException{
        ConnectStatus connectStatus = state.get();
        if(connectStatus != ConnectStatus.READY)
            throw new IllegalStateException("client not ready");
        ChannelFuture channelFuture = connector.bootstrap.connect(addressPort.address, addressPort.port).sync();
        connector.setChannelFuture(channelFuture);
        state.compareAndSet(ConnectStatus.CONNECTED,ConnectStatus.READY);
    }

    @Override
    public void disconnect() throws InterruptedException {
        ConnectStatus connectStatus = state.get();
        if(connectStatus != ConnectStatus.CONNECTED)
            throw new IllegalStateException("client not connected");
        // 断开连接操作
        connector.workerGroup.shutdownGracefully().sync();
        connector.setChannelFuture(null);
        state.compareAndSet(ConnectStatus.CONNECTED,ConnectStatus.READY);
    }

    @Override
    public String getToken() {
        return token;
    }

    @Override
    public <R extends Action.ActionResponse,T extends Action<R>> R action(T action) {
        if (state.get()!=ConnectStatus.CONNECTED)
            throw new IllegalStateException("client not connected");
        // 写数据
        ChannelFuture future = connector.channelFuture.channel().writeAndFlush("");

        return null;
    }

    // handleEvent 处理通知
    public void handleEvent(Event event) {

    }

    // handleEvent 处理响应
    public void handleResponse(Event event) {

    }





    private class AddressPort{
        final String address;
        final int port;

        final boolean useSSL;

        private final static String SCHEMA = "ws://";

        private final static String SCHEMA_SSL = "wss://";

        private final static String PATH = "/jsonrpc";

        private final URI uri;

        AddressPort(String address,int port){
            this(address,port,false);
        }
        AddressPort(String address,int port,boolean useSSL){
            this.address = address;
            this.port = port;
            this.useSSL = useSSL;
            String schema = useSSL ? SCHEMA_SSL : SCHEMA;
            String uriStr = new StringBuilder(schema).append(address).append(":").append(port).append(PATH).toString();
            this.uri = URI.create(uriStr);
        }

        public URI getUri(){
            return uri;
        }

    }

    private class Connector{
        final Bootstrap bootstrap;
        final EventLoopGroup workerGroup;
        volatile ChannelFuture channelFuture;
        Connector(Bootstrap bootstrap,EventLoopGroup workerGroup){
            this.workerGroup = workerGroup;
            this.bootstrap = bootstrap;
        }

        public void setChannelFuture(ChannelFuture channelFuture) {
            this.channelFuture = channelFuture;
        }
    }
}
