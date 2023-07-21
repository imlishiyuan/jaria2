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
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.DefaultThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLException;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Default Aria2Client
 * @author lee
 */
public class DefaultAria2Client implements Aria2Client{

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultAria2Client.class);

    private static final long ZERO = 0L;
    private static final int HTTP_MAX_CONTENT_LENGTH = 8192;

    private final Aria2Config aria2Config;

    private final AtomicReference<ConnectStatus> state;

    private final List<EventProcessor> processor = new ArrayList<>();

    private volatile Connector connector;

    public DefaultAria2Client(String token){
        this(Aria2Config.Client.DEFAULT_ADDRESS,Aria2Config.Client.DEFAULT_PORT,token,Aria2Config.Client.DEFAULT_USE_SSL);
    }

    public DefaultAria2Client(String address, int port,String token,boolean useSSL) {
        Aria2Config aria2Config = new Aria2Config();
        Aria2Config.Client client = new Aria2Config.Client();
        client.setAddress(address);
        client.setPort(port);
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
        final SslContext sslContext;
        try {
            sslContext = SslContextBuilder.forClient().build();
        } catch (SSLException e) {
            throw new RuntimeException(e.getMessage(),e);
        }
        Aria2HeartbeatSendHandler aria2HeartbeatSendHandler = new Aria2HeartbeatSendHandler();
        LoggingHandler loggingHandler = new LoggingHandler();
        // WebSocketClientHandshaker 30s timeout
        WebSocketClientProtocolHandler webSocketClientProtocolHandler = new WebSocketClientProtocolHandler(addressPort.getUri(), WebSocketVersion.V13, null, true, new DefaultHttpHeaders(), 65536,  false, true,true,300_000);
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
                        ChannelPipeline pipeline = ch.pipeline();
                        if(addressPort.useSSL){
                            pipeline.addLast("Aria2_SslHandler",sslContext.newHandler(ch.alloc()));
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
            LOGGER.info("connect to {} success",addressPort.getUri());
        }

        Runtime.getRuntime().addShutdownHook(new Thread(()-> {
            try {
                if (state.get() == ConnectStatus.CONNECTED){
                    disconnect();
                }
            } catch (InterruptedException e) {
                LOGGER.error(e.getMessage(),e);
            }
        }));
    }

    @Override
    public void disconnect() throws InterruptedException {
        ConnectStatus connectStatus = state.get();
        if(connectStatus != ConnectStatus.CONNECTED)
            throw new StatusException("client not connected");
        // 断开连接操作
        connector.workerGroup.shutdownGracefully().sync();
        processor.addAll(connector.aria2MessageHandler.getEventProcessors());
        CACHE.clear();
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
            LOGGER.error("call aria2 error . msg : "+e.getMessage());
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
