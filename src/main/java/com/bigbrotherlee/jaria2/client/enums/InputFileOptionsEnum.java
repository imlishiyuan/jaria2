package com.bigbrotherlee.jaria2.client.enums;

/**
 * URL选项
 */
public enum InputFileOptionsEnum {
    ALL_PROXY("all-proxy"),
    ALL_PROXY_PASSWD("all-proxy-passwd"),
    ALL_PROXY_USER("all-proxy-user"),
    ALLOW_OVERWRITE("allow-overwrite"),
    ALLOW_PIECE_LENGTH_CHANGE("allow-piece-length-change"),
    ALWAYS_RESUME("always-resume"),
    ASYNC_DNS("async-dns"),
    AUTO_FILE_RENAMING("auto-file-renaming"),
    BT_ENABLE_HOOK_AFTER_HASH_CHECK("bt-enable-hook-after-hash-check"),
    BT_ENABLE_LPD("bt-enable-lpd"),
    BT_EXCLUDE_TRACKER("bt-exclude-tracker"),
    BT_EXTERNAL_IP("bt-external-ip"),
    BT_FORCE_ENCRYPTION("bt-force-encryption"),
    BT_HASH_CHECK_SEED("bt-hash-check-seed"),
    BT_LOAD_SAVED_METADATA("bt-load-saved-metadata"),
    BT_MAX_PEERS("bt-max-peers"),
    BT_METADATA_ONLY("bt-metadata-only"),
    BT_MIN_CRYPTO_LEVEL("bt-min-crypto-level"),
    BT_PRIORITIZE_PIECE("bt-prioritize-piece"),
    BT_REMOVE_UNSELECTED_FILE("bt-remove-unselected-file"),
    BT_REQUEST_PEER_SPEED_LIMIT("bt-request-peer-speed-limit"),
    BT_REQUIRE_CRYPTO("bt-require-crypto"),
    BT_SAVE_METADATA("bt-save-metadata"),
    BT_SEED_UNVERIFIED("bt-seed-unverified"),
    BT_STOP_TIMEOUT("bt-stop-timeout"),
    BT_TRACKER("bt-tracker"),
    BT_TRACKER_CONNECT_TIMEOUT("bt-tracker-connect-timeout"),
    BT_TRACKER_INTERVAL("bt-tracker-interval"),
    BT_TRACKER_TIMEOUT("bt-tracker-timeout"),
    CHECK_INTEGRITY("check-integrity"),
    CHECKSUM("checksum"),
    CONDITIONAL_GET("conditional-get"),
    CONNECT_TIMEOUT("connect-timeout"),
    CONTENT_DISPOSITION_DEFAULT_UTF8("content-disposition-default-utf8"),
    CONTINUE("continue"),
    DIR("dir"),
    DRY_RUN("dry-run"),
    ENABLE_HTTP_KEEP_ALIVE("enable-http-keep-alive"),
    ENABLE_HTTP_PIPELINING("enable-http-pipelining"),
    ENABLE_MMAP("enable-mmap"),
    ENABLE_PEER_EXCHANGE("enable-peer-exchange"),
    FILE_ALLOCATION("file-allocation"),
    FOLLOW_METALINK("follow-metalink"),
    FOLLOW_TORRENT("follow-torrent"),
    FORCE_SAVE("force-save"),
    FTP_PASSWD("ftp-passwd"),
    FTP_PASV("ftp-pasv"),
    FTP_PROXY("ftp-proxy"),
    FTP_PROXY_PASSWD("ftp-proxy-passwd"),
    FTP_PROXY_USER("ftp-proxy-user"),
    FTP_REUSE_CONNECTION("ftp-reuse-connection"),
    FTP_TYPE("ftp-type"),
    FTP_USER("ftp-user"),
    GID("gid"),
    HASH_CHECK_ONLY("hash-check-only"),
    HEADER("header"),
    HTTP_ACCEPT_GZIP("http-accept-gzip"),
    HTTP_AUTH_CHALLENGE("http-auth-challenge"),
    HTTP_NO_CACHE("http-no-cache"),
    HTTP_PASSWD("http-passwd"),
    HTTP_PROXY("http-proxy"),
    HTTP_PROXY_PASSWD("http-proxy-passwd"),
    HTTP_PROXY_USER("http-proxy-user"),
    HTTP_USER("http-user"),
    HTTPS_PROXY("https-proxy"),
    HTTPS_PROXY_PASSWD("https-proxy-passwd"),
    HTTPS_PROXY_USER("https-proxy-user"),
    INDEX_OUT("index-out"),
    LOWEST_SPEED_LIMIT("lowest-speed-limit"),
    MAX_CONNECTION_PER_SERVER("max-connection-per-server"),
    MAX_DOWNLOAD_LIMIT("max-download-limit"),
    MAX_FILE_NOT_FOUND("max-file-not-found"),
    MAX_MMAP_LIMIT("max-mmap-limit"),
    MAX_RESUME_FAILURE_TRIES("max-resume-failure-tries"),
    MAX_TRIES("max-tries"),
    MAX_UPLOAD_LIMIT("max-upload-limit"),
    METALINK_BASE_URI("metalink-base-uri"),
    METALINK_ENABLE_UNIQUE_PROTOCOL("metalink-enable-unique-protocol"),
    METALINK_LANGUAGE("metalink-language"),
    METALINK_LOCATION("metalink-location"),
    METALINK_OS("metalink-os"),
    METALINK_PREFERRED_PROTOCOL("metalink-preferred-protocol"),
    METALINK_VERSION("metalink-version"),
    MIN_SPLIT_SIZE("min-split-size"),
    NO_FILE_ALLOCATION_LIMIT("no-file-allocation-limit"),
    NO_NETRC("no-netrc"),
    NO_PROXY("no-proxy"),
    OUT("out"),
    PARAMETERIZED_URI("parameterized-uri"),
    PAUSE("pause"),
    PAUSE_METADATA("pause-metadata"),
    PIECE_LENGTH("piece-length"),
    PROXY_METHOD("proxy-method"),
    REALTIME_CHUNK_CHECKSUM("realtime-chunk-checksum"),
    REFERER("referer"),
    REMOTE_TIME("remote-time"),
    REMOVE_CONTROL_FILE("remove-control-file"),
    RETRY_WAIT("retry-wait"),
    REUSE_URI("reuse-uri"),
    RPC_SAVE_UPLOAD_METADATA("rpc-save-upload-metadata"),
    SEED_RATIO("seed-ratio"),
    SEED_TIME("seed-time"),
    SELECT_FILE("select-file"),
    SPLIT("split"),
    SSH_HOST_KEY_MD("ssh-host-key-md"),
    STREAM_PIECE_SELECTOR("stream-piece-selector"),
    TIMEOUT("timeout"),
    URI_SELECTOR("uri-selector"),
    USE_HEAD("use-head"),
    USER_AGENT("user-agent");

    public final String option;

    InputFileOptionsEnum(String option) {
        this.option = option;
    }
}
