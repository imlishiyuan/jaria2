package cn.lishiyuan.jaria2.client;

import cn.lishiyuan.jaria2.client.action.Action;
import cn.lishiyuan.jaria2.client.action.ActionResponse;
import cn.lishiyuan.jaria2.client.event.process.EventProcessor;
import cn.lishiyuan.jaria2.client.handler.Aria2ActionSendHandler;
import cn.lishiyuan.jaria2.client.handler.Aria2HandshakeHandler;
import cn.lishiyuan.jaria2.client.handler.Aria2HeartbeatSendHandler;
import cn.lishiyuan.jaria2.client.handler.Aria2MessageHandler;
import cn.lishiyuan.jaria2.config.Aria2AddressPort;
import cn.lishiyuan.jaria2.config.Aria2Config;
import cn.lishiyuan.jaria2.exception.Aria2ActionException;
import cn.lishiyuan.jaria2.exception.StatusException;
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
import io.netty.handler.ssl.SslProvider;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.DefaultThreadFactory;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLException;
import java.io.File;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Default Aria2Client
 * @author lee
 */
@Slf4j
public class DefaultAria2Client implements Aria2Client{

    private final Map<String,CompletableFuture<String>> CACHE = new WeakHashMap<>(128);

    private static final long ZERO = 0L;
    private static final int HTTP_MAX_CONTENT_LENGTH = 8192;

    private final Aria2Config aria2Config;

    private final AtomicReference<ConnectStatus> state;

    private final List<EventProcessor> processor = new ArrayList<>();

    private volatile Connector connector;

    public DefaultAria2Client(String token){
        Aria2Config aria2Config = new Aria2Config();
        Aria2Config.Client client = new Aria2Config.Client();
        client.setAddress(Aria2Config.Client.DEFAULT_ADDRESS);
        client.setPort(Aria2Config.Client.DEFAULT_PORT);
        client.setToken(token);
        aria2Config.setClient(client);
        this.aria2Config = aria2Config;
        state = new AtomicReference<>(ConnectStatus.READY);
    }


    public DefaultAria2Client(Aria2Config config){
        if(Objects.isNull(config) || Objects.isNull(config.getClient())){
            throw new IllegalArgumentException("config or client can not be null");
        }
        this.aria2Config = config;
        state = new AtomicReference<>(ConnectStatus.READY);
    }

    @Override
    public void connect() throws InterruptedException{
        Aria2AddressPort addressPort = aria2Config.getClient().buildAria2AddressPort();
        ConnectStatus connectStatus = state.get();
        if(connectStatus != ConnectStatus.READY)
            throw new StatusException("client not ready");
        EventLoopGroup workerGroup = new NioEventLoopGroup(new DefaultThreadFactory("aria2-client"));
        Bootstrap bootstrap = new Bootstrap();

        Aria2HeartbeatSendHandler aria2HeartbeatSendHandler = new Aria2HeartbeatSendHandler(aria2Config.getClient().getHeartbeatMaxTimes());
        LoggingHandler loggingHandler = new LoggingHandler();
        WebSocketClientProtocolHandler webSocketClientProtocolHandler = new WebSocketClientProtocolHandler(addressPort.getUri(), WebSocketVersion.V13, null, true, new DefaultHttpHeaders(), HTTP_MAX_CONTENT_LENGTH,  false, true,true,Aria2Config.Client.DEFAULT_TIME_UNIT.toMillis(aria2Config.getClient().getConnectTimeout()));
        Aria2HandshakeHandler aria2HandshakeHandler = new Aria2HandshakeHandler();
        Aria2MessageHandler aria2MessageHandler = Aria2MessageHandler.newInstance();
        bootstrap
                .group(workerGroup)
                .option(ChannelOption.SO_KEEPALIVE,true)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS,
                        Long.valueOf(
                                Aria2Config.Client.DEFAULT_TIME_UNIT.toMillis(aria2Config.getClient().getConnectTimeout())
                        ).intValue())
                .channel(NioSocketChannel.class)
                .handler(loggingHandler)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) {
                        ch.attr(Aria2Config.Client.ARIA2_CLIENT_ATTRIBUTE_KEY).set(DefaultAria2Client.this);
                        ch.attr(Aria2Config.Client.ARIA2_HEARTBEAT_SEND_HANDLER_ATTRIBUTE_KEY).set(aria2HeartbeatSendHandler);

                        ChannelPipeline pipeline = ch.pipeline();
                        if(addressPort.useSSL){
                            final SslContext sslContext;
                            try {
                                sslContext = SslContextBuilder.forClient().sslProvider(SslProvider.OPENSSL).trustManager(new File(aria2Config.getClient().getKeyPath())).build();
                                pipeline.addLast("Aria2_SslHandler",sslContext.newHandler(ch.alloc()));
                            } catch (SSLException e) {
                                throw new RuntimeException(e.getMessage(),e);
                            }
                        }
                        pipeline
                                .addLast("Aria2_Logger",loggingHandler)
                                .addLast("Aria2_IdleStateHandler",new IdleStateHandler(aria2Config.getClient().getResponseTimeout(),aria2Config.getClient().getHeartbeatInterval(),ZERO,Aria2Config.Client.DEFAULT_TIME_UNIT))
                                .addLast("Aria2_HttpClientCodec",new HttpClientCodec())
                                .addLast("Aria2_HttpObjectAggregator",new HttpObjectAggregator(HTTP_MAX_CONTENT_LENGTH))
                                .addLast(WebSocketClientCompressionHandler.INSTANCE)
                                .addLast("WebSocketClientProtocolHandler",webSocketClientProtocolHandler)
                                .addLast("Aria2HandshakeHandler",aria2HandshakeHandler)
                                .addLast("Aria2MessageHandler", aria2MessageHandler)
                                .addLast("Aria2ActionSendHandler", Aria2ActionSendHandler.newInstance())
                                .addLast("Aria2HeartbeatHandler", aria2HeartbeatSendHandler);
                    }
                });
        aria2MessageHandler.addEventProcessors(processor);
        processor.clear();
        Channel channel = bootstrap.connect(addressPort.address, addressPort.port).sync().channel();
        // 连接
        this.connector = new Connector(bootstrap, workerGroup, aria2HandshakeHandler, aria2MessageHandler, channel);

        if(aria2HandshakeHandler.getHandshake().sync().isSuccess()){
            state.compareAndSet(ConnectStatus.READY,ConnectStatus.CONNECTED);
            log.info("connect to {} success",addressPort.getUri());
        }

        Runtime.getRuntime().addShutdownHook(new Thread(()-> {
            try {
                disconnect();
            } catch (InterruptedException e) {
                log.error(e.getMessage(),e);
            }
        }));
    }

    @Override
    public void disconnect() throws InterruptedException {
        ConnectStatus connectStatus = state.get();
        if(connectStatus != ConnectStatus.CONNECTED)
            throw new StatusException("client not connected");
        // 断开连接操作
        CACHE.clear();
        connector.workerGroup.shutdownGracefully().sync();
        processor.addAll(connector.aria2MessageHandler.getEventProcessors());
        connector = null;
        state.compareAndSet(ConnectStatus.CONNECTED,ConnectStatus.READY);
    }

    @Override
    public String getToken() {
        return aria2Config.getClient().getToken();
    }

    @Override
    public <R extends ActionResponse,T extends Action<R>> R action(T action) throws Aria2ActionException {
        if (state.get() != ConnectStatus.CONNECTED)
            throw new StatusException("client not connected");
        // 写数据
        action.setToken(aria2Config.getClient().getToken());
        connector.channel.writeAndFlush(action);
        CompletableFuture<String> response = new CompletableFuture<>();
        CACHE.put(action.getId(),response);
        try{
            String actionResponseStr = response.get(aria2Config.getClient().getResponseTimeout(),TimeUnit.SECONDS);
            return action.buildRespFromStr(actionResponseStr);
        }catch (ExecutionException | InterruptedException | TimeoutException e){
            log.error("call aria2 error . msg : "+e.getMessage());
            throw new Aria2ActionException(e);
        }finally {
            CACHE.remove(action.getId());
        }
    }

    @Override
    public void addEventProcessor(EventProcessor eventProcessor) {
        if (Objects.isNull(state))
            throw new StatusException("client not active");
        if(Objects.isNull(connector)){
            processor.add(eventProcessor);
        }else {
            connector.aria2MessageHandler.addEventProcessor(eventProcessor);
        }
    }

    @Override
    public void addEventProcessors(EventProcessor... eventProcessor) {
        if (Objects.isNull(state))
            throw new StatusException("client not active");
        if(Objects.isNull(connector)){
            processor.addAll(Arrays.asList(eventProcessor));
        }else {
            connector.aria2MessageHandler.addEventProcessors(eventProcessor);
        }
    }

    @Override
    public Map<String, CompletableFuture<String>> getCache() {
        return CACHE;
    }

    @Override
    public ConnectStatus getStatus() {
        return state.get();
    }

    /**
     * save connector info
     */
    private static class Connector{
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
