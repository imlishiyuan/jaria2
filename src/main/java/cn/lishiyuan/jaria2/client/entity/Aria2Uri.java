package cn.lishiyuan.jaria2.client.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Aria2Uri {
    private String uri;
    private String status;
    private String usedLength;
    private String seeder;
}