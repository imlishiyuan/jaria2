package com.bigbrotherlee.jaria2.config;

import java.nio.channels.Pipe;

/**
 * configuration
 * @author lee
 */
public class Aria2Config {
    private Manager manager;

    private Client client;

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return client;
    }

    /**
     * Aria2 Manager Config
     */
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

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String[] getArgs() {
            return args;
        }

        public void setArgs(String[] args) {
            this.args = args;
        }
    }

    public static class Client{
        public static final String DEFAULT_ADDRESS = "localhost";
        public static final int DEFAULT_PORT = 6800;
        public static final int DEFAULT_INTERVAL = 3;

        private static final long DEFAULT_TIMEOUT = 50000;
        public static final boolean DEFAULT_USE_SSL = false;

        private String address = DEFAULT_ADDRESS;

        private int port = DEFAULT_PORT;

        private boolean useSSL = DEFAULT_USE_SSL;

        private long responseTimeout = DEFAULT_TIMEOUT;

        private String token;

        private long heartbeatInterval = DEFAULT_INTERVAL;

        public long getHeartbeatInterval() {
            return heartbeatInterval;
        }


        public String getToken() {
            return token;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public boolean isUseSSL() {
            return useSSL;
        }

        public void setUseSSL(boolean useSSL) {
            this.useSSL = useSSL;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public long getResponseTimeout() {
            return responseTimeout;
        }

        public void setResponseTimeout(long responseTimeout) {
            this.responseTimeout = responseTimeout;
        }

        public void setHeartbeatInterval(long heartbeatInterval) {
            this.heartbeatInterval = heartbeatInterval;
        }

        public Aria2AddressPort buildAria2AddressPort(){
            return new Aria2AddressPort(address,port,useSSL);
        }

        @Override
        public String toString() {
            return "Client{" +
                    "address='" + address + '\'' +
                    ", port=" + port +
                    ", useSSL=" + useSSL +
                    ", token='" + token + '\'' +
                    ", heartbeatInterval=" + heartbeatInterval +
                    '}';
        }
    }
}
