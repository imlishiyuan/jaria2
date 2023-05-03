package cn.lishiyuan.jaria2.client.entity;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

/**
 * configuration options
 */
@Getter
@Setter
public class Aria2Options {
    /** enable-http-pipelining=false */
    @JSONField(name = "enable-http-pipelining")
    private Boolean enableHttpPipelining;

    /** auto-file-renaming=true */
    @JSONField(name = "auto-file-renaming")
    private Boolean autoFileRenaming;

    /** no-file-allocation-limit=5242880 */
    @JSONField(name = "no-file-allocation-limit")
    private Long noFileAllocationLimit;

    /** remote-time=false */
    @JSONField(name = "remote-time")
    private Boolean remoteTime;

    /** console-log-level=notice */
    @JSONField(name = "console-log-level")
    private String consoleLogLevel;

    /** proxy-method=get */
    @JSONField(name = "proxy-method")
    private String proxyMethod;

    /** bt-enable-hook-after-hash-check=true */
    @JSONField(name = "bt-enable-hook-after-hash-check")
    private Boolean btEnableHookAfterHashCheck;

    /** enable-peer-exchange=true */
    @JSONField(name = "enable-peer-exchange")
    private Boolean enablePeerExchange;

    /** dscp=0 */
    @JSONField(name = "dscp")
    private Long dscp;

    /** retry-wait=0 */
    @JSONField(name = "retry-wait")
    private Long retryWait;

    /** dht-file-path=/home/lee/.cache/aria2/dht.dat */
    @JSONField(name = "dht-file-path")
    private String dhtFilePath;

    /** max-file-not-found=0 */
    @JSONField(name = "max-file-not-found")
    private Integer maxFileNotFound;

    /** parameterized-uri=false */
    @JSONField(name = "parameterized-uri")
    private Boolean parameterizedUri;

    /** peer-agent=aria2/1.36.0 */
    @JSONField(name = "peer-agent")
    private String peerAgent;

    /** uri-selector=feedback */
    @JSONField(name = "uri-selector")
    private String uriSelector;

    /** download-result=default */
    @JSONField(name = "download-result")
    private String downloadResult;

    /** bt-remove-unselected-file=false */
    @JSONField(name = "bt-remove-unselected-file")
    private Boolean btRemoveUnselectedFile;

    /** bt-tracker-timeout=60 */
    @JSONField(name = "bt-tracker-timeout")
    private Integer btTrackerTimeout;

    /** stream-piece-selector=default */
    @JSONField(name = "stream-piece-selector")
    private String streamPieceSelector;

    /** no-netrc=false */
    @JSONField(name = "no-netrc")
    private Boolean noNetrc;

    /** enable-http-keep-alive=true */
    @JSONField(name = "enable-http-keep-alive")
    private Boolean enableHttpKeepAlive;

    /** ftp-type=binary */
    @JSONField(name = "ftp-type")
    private String ftpType;

    /** realtime-chunk-checksum=true */
    @JSONField(name = "realtime-chunk-checksum")
    private Boolean realtimeChunkChecksum;

    /** content-disposition-default-utf8=false */
    @JSONField(name = "content-disposition-default-utf8")
    private Boolean contentDispositionDefaultUtf8;

    /** file-allocation=prealloc */
    @JSONField(name = "file-allocation")
    private String fileAllocation;

    /** bt-seed-unverified=false */
    @JSONField(name = "bt-seed-unverified")
    private String btSeedUnverified;

    /** bt-stop-timeout=0 */
    @JSONField(name = "bt-stop-timeout")
    private String btStopTimeout;

    /** max-download-limit=0 */
    @JSONField(name = "max-download-limit")
    private Long maxDownloadLimit;

    /** max-tries=5 */
    @JSONField(name = "max-tries")
    private Integer maxTries;

    /** stop=0 */
    @JSONField(name = "stop")
    private Integer stop;

    /** bt-tracker-interval=0 */
    @JSONField(name = "bt-tracker-interval")
    private Long btTrackerInterval;

    /** use-head=false */
    @JSONField(name = "use-head")
    private Boolean useHead;

    /** rpc-max-request-size=2097152 */
    @JSONField(name = "rpc-max-request-size")
    private Long rpcMaxRequestSize;

    /** enable-color=true */
    @JSONField(name = "enable-color")
    private Boolean enableColor;

    /** bt-save-metadata=false */
    @JSONField(name = "bt-save-metadata")
    private Boolean btSaveMetadata;

    /** rpc-save-upload-metadata=true */
    @JSONField(name = "rpc-save-upload-metadata")
    private Boolean rpcSaveUploadMetadata;

    /** save-session-interval=30 */
    @JSONField(name = "save-session-interval")
    private Long saveSessionInterval;

    /** dht-message-timeout=10 */
    @JSONField(name = "dht-message-timeout")
    private Long dhtMessageTimeout;

    /** dht-file-path6=/home/lee/.cache/aria2/dht6.dat */
    @JSONField(name = "dht-file-path6")
    private String dhtFilePath6;

    /** ftp-pasv=true */
    @JSONField(name = "ftp-pasv")
    private Boolean ftpPasv;

    /** lowest-speed-limit=0 */
    @JSONField(name = "lowest-speed-limit")
    private Long lowestSpeedLimit;

    /** metalink-preferred-protocol=none */
    @JSONField(name = "metalink-preferred-protocol")
    private String metalinkPreferredProtocol;

    /** max-overall-upload-limit=0 */
    @JSONField(name = "max-overall-upload-limit")
    private Long maxOverallUploadLimit;

    /** follow-metalink=true */
    @JSONField(name = "follow-metalink")
    private Boolean followMetalink;

    /** deferred-input=false */
    @JSONField(name = "deferred-input")
    private Boolean deferredInput;

    /** http-accept-gzip=false */
    @JSONField(name = "http-accept-gzip")
    private Boolean httpAcceptGzip;

    /** force-save=false */
    @JSONField(name = "force-save")
    private Boolean forceSave;

    /** quiet=false */
    @JSONField(name = "quiet")
    private Boolean quiet;

    /** enable-rpc=true */
    @JSONField(name = "enable-rpc")
    private Boolean enableRpc;

    /** listen-port=6881-6999 */
    @JSONField(name = "listen-port")
    private String listenPort;

    /** dry-run=false */
    @JSONField(name = "dry-run")
    private Boolean dryRun;

    /** bt-metadata-only=false */
    @JSONField(name = "bt-metadata-only")
    private Boolean btMetadataOnly;

    /** bt-require-crypto=false */
    @JSONField(name = "bt-require-crypto")
    private Boolean btRequireCrypto;

    /** socket-recv-buffer-size=0 */
    @JSONField(name = "socket-recv-buffer-size")
    private Long socketRecvBufferSize;

    /** help=#basic */
    @JSONField(name = "help")
    private String help;

    /** rlimit-nofile=1024 */
    @JSONField(name = "rlimit-nofile")
    private Long rlimitNofile;

    /** rpc-secure=false */
    @JSONField(name = "rpc-secure")
    private Boolean rpcSecure;

    /** save-session=/home/lee/桌面/aria2-download/aria2.session */
    @JSONField(name = "save-session")
    private String saveSession;

    /** conditional-get=false */
    @JSONField(name = "conditional-get")
    private Boolean conditionalGet;

    /** metalink-enable-unique-protocol=true */
    @JSONField(name = "metalink-enable-unique-protocol")
    private Boolean metalinkEnableUniqueProtocol;

    /** bt-max-open-files=100 */
    @JSONField(name = "bt-max-open-files")
    private Long btMaxOpenFiles;

    /** async-dns=true */
    @JSONField(name = "async-dns")
    private Boolean asyncDns;

    /** peer-id-prefix=A2-1-36-0- */
    @JSONField(name = "peer-id-prefix")
    private String peerIdPrefix;

    /** optimize-concurrent-downloads=false */
    @JSONField(name = "optimize-concurrent-downloads")
    private Boolean optimizeConcurrentDownloads;

    /** user-agent=aria2/1.36.0 */
    @JSONField(name = "user-agent")
    private String userAgent;

    /** rpc-listen-all=false */
    @JSONField(name = "rpc-listen-all")
    private Boolean rpcListenAll;

    /** max-resume-failure-tries=0 */
    @JSONField(name = "max-resume-failure-tries")
    private Integer maxResumeFailureTries;

    /** bt-hash-check-seed=true */
    @JSONField(name = "bt-hash-check-seed")
    private Boolean btHashCheckSeed;

    /** dir=/home/lee/IdeaProjects/jaria2 */
    @JSONField(name = "dir")
    private String dir;

    /** split=5 */
    @JSONField(name = "split")
    private Integer split;

    /** allow-piece-length-change=false */
    @JSONField(name = "allow-piece-length-change")
    private Boolean allowPieceLengthChange;

    /** max-overall-download-limit=0 */
    @JSONField(name = "max-overall-download-limit")
    private Long maxOverallDownloadLimit;

    /** continue=false */
    @JSONField(name = "continue")
    private Boolean isContinue;

    /** http-no-cache=false */
    @JSONField(name = "http-no-cache")
    private Boolean httpNoCache;

    /** min-split-size=20971520 */
    @JSONField(name = "min-split-size")
    private String minSplitSize;

    /** max-mmap-limit=9223372036854775807 */
    @JSONField(name = "max-mmap-limit")
    private Long maxMmapLimit;

    /** max-download-result=1000 */
    @JSONField(name = "max-download-result")
    private Integer maxDownloadResult;

    /** always-resume=true */
    @JSONField(name = "always-resume")
    private Boolean alwaysResume;

    /** ca-certificate=/etc/ssl/certs/ca-certificates.crt */
    @JSONField(name = "ca-certificate")
    private String caCertificate;

    /** show-files=false */
    @JSONField(name = "show-files")
    private Boolean showFiles;

    /** stderr=false */
    @JSONField(name = "stderr")
    private Boolean stderr;

    /** seed-ratio=1.0 */
    @JSONField(name = "seed-ratio")
    private String seedRatio;

    /** daemon=false */
    @JSONField(name = "daemon")
    private Boolean daemon;

    /** no-conf=false */
    @JSONField(name = "no-conf")
    private Boolean noConf;

    /** max-connection-per-server=1 */
    @JSONField(name = "max-connection-per-server")
    private Integer maxConnectionPerServer;

    /** log-level=debug */
    @JSONField(name = "log-level")
    private String logLevel;

    /** bt-min-crypto-level=plain */
    @JSONField(name = "bt-min-crypto-level")
    private String btMinCryptoLevel;

    /** show-console-readout=true */
    @JSONField(name = "show-console-readout")
    private Boolean showConsoleReadout;

    /** human-readable=true */
    @JSONField(name = "human-readable")
    private Boolean humanReadable;

    /** remove-control-file=false */
    @JSONField(name = "remove-control-file")
    private Boolean removeControlFile;

    /** netrc-path=/home/lee/.netrc */
    @JSONField(name = "netrc-path")
    private String netrcPath;

    /** bt-force-encryption=false */
    @JSONField(name = "bt-force-encryption")
    private Boolean btForceEncryption;

    /** min-tls-version=TLSv1.2 */
    @JSONField(name = "min-tls-version")
    private String minTlsVersion;

    /** bt-detach-seed-only=false */
    @JSONField(name = "bt-detach-seed-only")
    private Boolean btDetachSeedOnly;

    /** bt-load-saved-metadata=false */
    @JSONField(name = "bt-load-saved-metadata")
    private Boolean btLoadSavedMetadata;

    /** ftp-reuse-connection=true */
    @JSONField(name = "ftp-reuse-connection")
    private Boolean ftpReuseConnection;

    /** enable-mmap=false */
    @JSONField(name = "enable-mmap")
    private Boolean enableMmap;

    /** max-concurrent-downloads=5 */
    @JSONField(name = "max-concurrent-downloads")
    private Integer maxConcurrentDownloads;

    /** timeout=60 */
    @JSONField(name = "timeout")
    private Integer timeout;

    /** auto-save-interval=30 */
    @JSONField(name = "auto-save-interval")
    private Integer autoSaveInterval;

    /** hash-check-only=false */
    @JSONField(name = "hash-check-only")
    private Boolean hashCheckOnly;

    /** bt-enable-lpd=false */
    @JSONField(name = "bt-enable-lpd")
    private Boolean btEnableLpd;

    /** truncate-console-readout=true */
    @JSONField(name = "truncate-console-readout")
    private Boolean truncateConsoleReadout;

    /** allow-overwrite=false */
    @JSONField(name = "allow-overwrite")
    private Boolean allowOverwrite;

    /** disable-ipv6=false */
    @JSONField(name = "disable-ipv6")
    private Boolean disableIpv6;

    /** disk-cache=16777216 */
    @JSONField(name = "disk-cache")
    private Long diskCache;

    /** enable-dht6=false */
    @JSONField(name = "enable-dht6")
    private Boolean enableDht6;

    /** enable-dht=true */
    @JSONField(name = "enable-dht")
    private Boolean enableDht;

    /** http-auth-challenge=false */
    @JSONField(name = "http-auth-challenge")
    private Boolean httpAuthChallenge;

    /** rpc-listen-port=6800 */
    @JSONField(name = "rpc-listen-port")
    private Integer rpcListenPort;

    /** follow-torrent=true */
    @JSONField(name = "follow-torrent")
    private Boolean followTorrent;

    /** bt-tracker-connect-timeout=60 */
    @JSONField(name = "bt-tracker-connect-timeout")
    private Integer btTrackerConnectTimeout;

    /** rpc-allow-origin-all=true */
    @JSONField(name = "rpc-allow-origin-all")
    private Boolean rpcAllowOriginAll;

    /** summary-interval=60 */
    @JSONField(name = "summary-interval")
    private Integer summaryInterval;

    /** dht-listen-port=6881-6999 */
    @JSONField(name = "dht-listen-port")
    private String dhtListenPort;

    /** max-upload-limit=0 */
    @JSONField(name = "max-upload-limit")
    private Long maxUploadLimit;

    /** server-stat-timeout=86400 */
    @JSONField(name = "server-stat-timeout")
    private Long serverStatTimeout;

    /** conf-path=/home/lee/.config/aria2/aria2.conf */
    @JSONField(name = "conf-path")
    private String confPath;

    /** pause-metadata=false */
    @JSONField(name = "pause-metadata")
    private Boolean pauseMetadata;

    /** check-integrity=false */
    @JSONField(name = "check-integrity")
    private Boolean checkIntegrity;

    /** connect-timeout=60 */
    @JSONField(name = "connect-timeout")
    private Long connectTimeout;

    /** bt-request-peer-speed-limit=51200 */
    @JSONField(name = "bt-request-peer-speed-limit")
    private Long btRequestPeerSpeedLimit;

    /** piece-length=1048576 */
    @JSONField(name = "piece-length")
    private Long pieceLength;

    /** check-certificate=true */
    @JSONField(name = "check-certificate")
    private Boolean checkCertificate;

    /** event-poll=epoll */
    @JSONField(name = "event-poll")
    private String eventPoll;

    /** keep-unfinished-download-result=true */
    @JSONField(name = "keep-unfinished-download-result")
    private Boolean keepUnfinishedDownloadResult;

    /** bt-max-peers=55 */
    @JSONField(name = "bt-max-peers")
    private Integer btMaxPeers;

    /** save-not-found=true */
    @JSONField(name = "save-not-found")
    private Boolean saveNotFound;

    /** reuse-uri=true */
    @JSONField(name = "reuse-uri")
    private Boolean reuseUri;

    /** out=deepin2.iso , only gid */
    @JSONField(name = "out")
    private String out;


}
