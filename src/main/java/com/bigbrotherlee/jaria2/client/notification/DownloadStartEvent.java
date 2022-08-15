package com.bigbrotherlee.jaria2.client.notification;

import com.bigbrotherlee.jaria2.client.enums.EventEnum;

public class DownloadStartEvent extends Event{
    protected DownloadStartEvent(String eventName) {
        super(EventEnum.START.name);
    }
}
