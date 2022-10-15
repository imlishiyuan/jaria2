package com.bigbrotherlee.jaria2.client.event;

import com.bigbrotherlee.jaria2.client.enums.EventEnum;

import java.util.List;

public class DownloadStartEvent extends BaseEvent{
    private static final EventEnum eventEnum = EventEnum.START;

    public DownloadStartEvent() {
        super(eventEnum);
    }
}
