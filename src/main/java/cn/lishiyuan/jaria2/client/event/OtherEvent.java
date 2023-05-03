package cn.lishiyuan.jaria2.client.event;

import cn.lishiyuan.jaria2.client.enums.EventEnum;

public class OtherEvent extends Event{
    private static final EventEnum eventEnum = EventEnum.OTHER;

    public OtherEvent() {
        super(eventEnum);
    }
}
