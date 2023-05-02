package com.bigbrotherlee.jaria2.client.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Aria2StatusInfo {
    private String gid;
    private String status;

    private String totalLength;

    private String completedLength;

    private String uploadLength;

    private String bitfield;

    private String downloadSpeed;

    private String uploadSpeed;

    private String infoHash;

    private String numSeeders;

    private String seeder;

    private String pieceLength;

    private String numPieces;

    private String connections;

    private String errorCode;

    private String errorMessage;

    private String followedBy;

    private String following;

    private String belongsTo;

    private String dir;

    private List<Aria2File> files;

    private Aria2BitTorrent bittorrent;

    private String verifiedLength;

    private String verifyIntegrityPending;
}

