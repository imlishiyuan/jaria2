package cn.lishiyuan.jaria2.client.event;

import cn.lishiyuan.jaria2.client.enums.EventEnum;

public class DownloadStartEvent extends BaseEvent{
    private static final EventEnum eventEnum = EventEnum.START;

    public DownloadStartEvent() {
        super(eventEnum);
    }
}
