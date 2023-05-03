package cn.lishiyuan.jaria2.config;

/**
 * aria2 recognizes the following environment variables.
 * aria2 能识别以下环境变量
 */
public enum Aria2EnvironmentEnum {

    HTTP_PROXY("http_proxy"),

    HTTPS_PROXY("https_proxy"),

    FTP_PROXY("ftp_proxy"),

    ALL_PROXY("all_proxy"),

    NO_PROXY("no_proxy");

    public final String value;

    Aria2EnvironmentEnum(String value) {
        this.value = value;
    }
}
