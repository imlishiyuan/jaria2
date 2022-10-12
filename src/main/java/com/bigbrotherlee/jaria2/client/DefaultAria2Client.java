package com.bigbrotherlee.jaria2.client;

import com.bigbrotherlee.jaria2.client.action.Action;
import com.bigbrotherlee.jaria2.client.event.process.EventProcessor;
import com.bigbrotherlee.jaria2.client.handler.Aria2ActionSendHandler;
import com.bigbrotherlee.jaria2.client.handler.Aria2HandshakeHandler;
import com.bigbrotherlee.jaria2.client.handler.Aria2HeartbeatSendHandler;
import com.bigbrotherlee.jaria2.client.handler.Aria2MessageHandler;
import com.bigbrotherlee.jaria2.exception.Aria2ActionException;
import com.bigbrotherlee.jaria2.exception.StatusException;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketClientCompressionHandler;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.DefaultThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Default Aria2Client
 * @author lee
 */
public class DefaultAria2Client implements Aria2Client{
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultAria2Client.class);

    private final AddressPort addressPort;

    private final AtomicReference<ConnectStatus> state;

    private final String token;

    private final List<EventProcessor> processor = new ArrayList<>();

    private Connector connector;

    public DefaultAria2Client(String token){
        this("localhost",6800,token,false);
    }

    public DefaultAria2Client(String address, int port,String token,boolean useSSL) {
        this.addressPort = new AddressPort(address,port,useSSL);
        this.token = token;
        state = new AtomicReference<>(ConnectStatus.READY);
    }

    @Override
    public void connect() throws InterruptedException{
        ConnectStatus connectStatus = state.get();
        if(connectStatus != ConnectStatus.READY)
            throw new StatusException("client not ready");
        EventLoopGroup workerGroup = new NioEventLoopGroup(new DefaultThreadFactory("aria2-client"));
        Bootstrap bootstrap = new Bootstrap();
        final SslContext sslContext;
        try {
            sslContext = SslContextBuilder.forClient().build();
        } catch (SSLException e) {
            throw new RuntimeException(e.getMessage(),e);
        }
        Aria2HeartbeatSendHandler aria2HeartbeatSendHandler = new Aria2HeartbeatSendHandler();
        LoggingHandler loggingHandler = new LoggingHandler();
        Aria2HandshakeHandler aria2HandshakeHandler = new Aria2HandshakeHandler(WebSocketClientHandshakerFactory.newHandshaker(addressPort.getUri(), WebSocketVersion.V13, null, true, new DefaultHttpHeaders()));
        Aria2MessageHandler aria2MessageHandler = Aria2MessageHandler.newInstance();
        bootstrap
                .group(workerGroup)
                .option(ChannelOption.SO_KEEPALIVE,true)
                .channel(NioSocketChannel.class)
                .handler(loggingHandler)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) {
                        ChannelPipeline pipeline = ch.pipeline();
                        if(addressPort.useSSL){
                            pipeline.addLast("Aria2_SslHandler",sslContext.newHandler(ch.alloc()));
                        }
                        pipeline
                                .addLast("Aria2_Logger",loggingHandler)
                                .addLast("Aria2_IdleStateHandler",new IdleStateHandler(0,3,0))
                                .addLast("Aria2_HttpClientCodec",new HttpClientCodec())
                                .addLast("Aria2_HttpObjectAggregator",new HttpObjectAggregator(8192))
                                .addLast(WebSocketClientCompressionHandler.INSTANCE)
                                .addLast("Aria2HandshakeHandler",aria2HandshakeHandler)
                                .addLast("Aria2MessageHandler", aria2MessageHandler)
                                .addLast("Aria2ActionSendHandler", Aria2ActionSendHandler.newInstance())
                                .addLast("Aria2HeartbeatHandler", aria2HeartbeatSendHandler);
                    }
                });
        aria2MessageHandler.addEventProcessors(processor.toArray(EventProcessor[]::new));
        processor.clear();
        Channel channel = bootstrap.connect(addressPort.address, addressPort.port).sync().channel();
        // 连接
        this.connector = new Connector(bootstrap,workerGroup,aria2HandshakeHandler, aria2MessageHandler,channel);

        if(aria2HandshakeHandler.getHandshake().sync().isSuccess()){
            state.compareAndSet(ConnectStatus.READY,ConnectStatus.CONNECTED);
        }
    }

    @Override
    public void disconnect() throws InterruptedException {
        ConnectStatus connectStatus = state.get();
        if(connectStatus != ConnectStatus.CONNECTED)
            throw new StatusException("client not connected");
        // 断开连接操作
        connector.workerGroup.shutdownGracefully().sync();
        processor.addAll(connector.aria2MessageHandler.getEventProcessors());
        connector = null;
        state.compareAndSet(ConnectStatus.CONNECTED,ConnectStatus.READY);
    }

    @Override
    public String getToken() {
        return token;
    }

    @Override
    public <R extends Action.ActionResponse,T extends Action<R>> R action(T action) throws Aria2ActionException {
        if (state.get()!=ConnectStatus.CONNECTED)
            throw new StatusException("client not connected");
        // 写数据
        connector.channel.writeAndFlush(action);
        CompletableFuture<String> response = new CompletableFuture<>();
        CACHE.put(action.getId(),response);
        try{
            String actionResponseStr = response.get(5,TimeUnit.SECONDS);
            return action.buildRespFromStr(actionResponseStr);
        }catch (ExecutionException | InterruptedException | TimeoutException e){
            LOGGER.error("call aria2 error . msg : "+e.getMessage());
            throw new Aria2ActionException(e);
        }finally {
            CACHE.remove(action.getId());
        }
    }

    @Override
    public void addEventProcessor(EventProcessor eventProcessor) {
        if (state == null)
            throw new StatusException("client not active");
        connector.aria2MessageHandler.addEventProcessor(eventProcessor);
    }

    @Override
    public void addEventProcessors(EventProcessor... eventProcessor) {
        if (state == null)
            throw new StatusException("client not active");
        connector.aria2MessageHandler.addEventProcessors(eventProcessor);
    }

    /**
     * save address info
     */
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

    /**
     * save connector info
     */
    private class Connector{
        final Bootstrap bootstrap;
        final EventLoopGroup workerGroup;
        final Channel channel;
        final Aria2HandshakeHandler aria2HandshakeHandler;

        final Aria2MessageHandler aria2MessageHandler;
        Connector(Bootstrap bootstrap, EventLoopGroup workerGroup, Aria2HandshakeHandler aria2HandshakeHandler, Aria2MessageHandler aria2MessageHandler,Channel channel){
            this.workerGroup = workerGroup;
            this.bootstrap = bootstrap;
            this.aria2HandshakeHandler = aria2HandshakeHandler;
            this.aria2MessageHandler = aria2MessageHandler;
            this.channel = channel;
        }
    }
}
