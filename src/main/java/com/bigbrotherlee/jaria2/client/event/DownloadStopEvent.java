package com.bigbrotherlee.jaria2.client.event;

import com.bigbrotherlee.jaria2.client.enums.EventEnum;

public class DownloadStopEvent extends BaseEvent{
    private static final EventEnum eventEnum = EventEnum.STOP;

    public DownloadStopEvent() {
        super(eventEnum);
    }
}
