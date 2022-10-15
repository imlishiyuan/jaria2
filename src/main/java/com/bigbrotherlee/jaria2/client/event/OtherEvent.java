package com.bigbrotherlee.jaria2.client.event;

import com.bigbrotherlee.jaria2.client.enums.EventEnum;

public class OtherEvent extends Event{
    private static final EventEnum eventEnum = EventEnum.OTHER;

    public OtherEvent() {
        super(eventEnum);
    }
}
