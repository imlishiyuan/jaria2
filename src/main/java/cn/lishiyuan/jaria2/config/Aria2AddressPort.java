package cn.lishiyuan.jaria2.config;

import java.net.URI;

/**
 * address Info
 */
public class Aria2AddressPort{
    public final String address;
    public final int port;

    public boolean useSSL;

    public final static String SCHEMA = "ws://";

    public final static String SCHEMA_SSL = "wss://";

    public final static String PATH = "/jsonrpc";

    public final URI uri;

    Aria2AddressPort(String address,int port,boolean useSSL){
        this.address = address;
        this.port = port;
        this.useSSL = useSSL;
        String schema = useSSL ? SCHEMA_SSL : SCHEMA;
        String uriStr = schema + address + ":" + port + PATH;
        this.uri = URI.create(uriStr);
    }

    public URI getUri(){
        return uri;
    }

}