package cn.lishiyuan.jaria2.config;

import cn.lishiyuan.jaria2.client.Aria2Client;
import cn.lishiyuan.jaria2.client.handler.Aria2HeartbeatSendHandler;
import io.netty.util.AttributeKey;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * configuration
 * @author lee
 */
@Data
public class Aria2Config {
    private Manager manager;

    private Client client;


    /**
     * Aria2 Manager Config
     */
    @Data
    public static class Manager{
        public static final String DEFAULT_PATH = "aria2c";

        /**
         * aria2c path
         */
        private String path;
        /**
         * aria2c args
         */
        private String[] args;
    }

    @Data
    public static class Client{

        public static final AttributeKey<Aria2Client> ARIA2_CLIENT_ATTRIBUTE_KEY = AttributeKey.valueOf("aria2Client");

        public static final AttributeKey<Aria2HeartbeatSendHandler> ARIA2_HEARTBEAT_SEND_HANDLER_ATTRIBUTE_KEY = AttributeKey.valueOf("heartbeatSendHandler");
        public static final TimeUnit DEFAULT_TIME_UNIT = TimeUnit.SECONDS;
        public static final String DEFAULT_ADDRESS = "localhost";
        public static final int DEFAULT_PORT = 6800;
        public static final int DEFAULT_INTERVAL = 5;

        public static final int DEFAULT_TIMES = 10;

        private static final long DEFAULT_TIMEOUT = 60;

        private String address = DEFAULT_ADDRESS;

        private int port = DEFAULT_PORT;

        private String keyPath = null;

        private long responseTimeout = DEFAULT_TIMEOUT;

        private long connectTimeout = DEFAULT_TIMEOUT;

        private String token;

        private long heartbeatInterval = DEFAULT_INTERVAL;

        private int heartbeatMaxTimes = DEFAULT_TIMES;


        public Aria2AddressPort buildAria2AddressPort(){
            return new Aria2AddressPort(address,port, StringUtils.isNotEmpty(keyPath));
        }

    }
}
