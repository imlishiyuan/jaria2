package com.bigbrotherlee.jaria2.client.event;

import com.alibaba.fastjson.JSONArray;
import com.bigbrotherlee.jaria2.client.enums.EventEnum;

import java.util.List;

public class DownloadCompleteEvent extends BaseEvent {
    private static final EventEnum eventEnum = EventEnum.COMPLETE;

    private List<EventContent> eventContents;

    public DownloadCompleteEvent() {
        super(eventEnum);
    }

    public List<EventContent> getEventContents() {
        return eventContents;
    }
}
