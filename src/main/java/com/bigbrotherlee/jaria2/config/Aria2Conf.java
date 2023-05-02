package com.bigbrotherlee.jaria2.config;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

/**
 * configuration options
 */
@Getter
@Setter
public class Aria2Conf {
    @JSONField(name = "enable-http-pipelining")
    private Boolean enableHttpPipelining;

    @JSONField(name = "auto-file-renaming")
    private Boolean autoFileRenaming;

    @JSONField(name = "no-file-allocation-limit")
    private Integer noFileAllocationLimit;

    @JSONField(name = "remote-time")
    private Boolean remoteTime;

    @JSONField(name = "console-log-level")
    private String consoleLogLevel;

    @JSONField(name = "proxy-method")
    private String proxyMethod;

    @JSONField(name = "bt-enable-hook-after-hash-check")
    private Boolean btEnableHookAfterHashCheck;

    @JSONField(name = "enable-peer-exchange")
    private Boolean enablePeerExchange;

    @JSONField(name = "dscp")
    private Integer dscp;

    @JSONField(name = "retry-wait")
    private Integer retryWait;

    @JSONField(name = "dht-file-path")
    private String dhtFilePath;

    @JSONField(name = "max-file-not-found")
    private Integer maxFileNotFound;

    @JSONField(name = "parameterized-uri")
    private Boolean parameterizedUri;

    @JSONField(name = "peer-agent")
    private String peerAgent;

    @JSONField(name = "uri-selector")
    private String uriSelector;

    @JSONField(name = "download-result")
    private String downloadResult;

    @JSONField(name = "bt-remove-unselected-file")
    private Boolean btRemoveUnselectedFile;

    @JSONField(name = "bt-tracker-timeout")
    private Integer btTrackerTimeout;

    @JSONField(name = "stream-piece-selector")
    private String streamPieceSelector;

    @JSONField(name = "no-netrc")
    private Boolean noNetrc;

    @JSONField(name = "enable-http-keep-alive")
    private Boolean enableHttpKeepAlive;

    @JSONField(name = "ftp-type")
    private String ftpType;

    @JSONField(name = "realtime-chunk-checksum")
    private Boolean realtimeChunkChecksum;

    @JSONField(name = "content-disposition-default-utf8")
    private Boolean contentDispositionDefaultUtf8;

    @JSONField(name = "file-allocation")
    private String fileAllocation;

    @JSONField(name = "bt-seed-unverified")
    private Boolean btSeedUnverified;

    @JSONField(name = "bt-stop-timeout")
    private Integer btStopTimeout;

    @JSONField(name = "max-download-limit")
    private Integer maxDownloadLimit;

    @JSONField(name = "max-tries")
    private Integer maxTries;

    @JSONField(name = "stop")
    private Integer stop;

    @JSONField(name = "bt-tracker-interval")
    private Integer btTrackerInterval;

    @JSONField(name = "use-head")
    private Boolean useHead;

    @JSONField(name = "rpc-max-request-size")
    private Integer rpcMaxRequestSize;

    @JSONField(name = "enable-color")
    private Boolean enableColor;

    @JSONField(name = "bt-save-metadata")
    private Boolean btSaveMetadata;

    @JSONField(name = "rpc-save-upload-metadata")
    private Boolean rpcSaveUploadMetadata;

    @JSONField(name = "save-session-interval")
    private Integer saveSessionInterval;

    @JSONField(name = "dht-message-timeout")
    private Integer dhtMessageTimeout;

    @JSONField(name = "dht-file-path6")
    private String dhtFilePath6;

    private String dir;

    private String log;

}
