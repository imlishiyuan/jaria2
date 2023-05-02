package com.bigbrotherlee.jaria2.client.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Aria2File{
    private String index;
    private String path;
    private String length;
    private String completedLength;
    private String selected;
    private List<Aria2Uri> uris;
}