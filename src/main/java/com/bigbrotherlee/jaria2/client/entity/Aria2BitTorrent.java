package com.bigbrotherlee.jaria2.client.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Aria2BitTorrent{
    private String announceList;
    private String comment;
    private String creationDate;
    private String mode;
    private Aria2BitTorrentInfo info;
}