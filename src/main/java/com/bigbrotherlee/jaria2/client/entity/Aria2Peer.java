package com.bigbrotherlee.jaria2.client.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Aria2Peer {
    private String peerId;
    private String ip;
    private Integer port;
    private String bitfield;
    private Boolean amChoking;
    private Boolean peerChoking;
    private String downloadSpeed;
    private String uploadSpeed;
    private Boolean seeder;
}