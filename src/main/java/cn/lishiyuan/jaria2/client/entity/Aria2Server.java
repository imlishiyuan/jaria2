package cn.lishiyuan.jaria2.client.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Aria2Server {
    private String uri;
    private String currentUri;
    private String downloadSpeed;
}