package cn.lishiyuan.jaria2.client.event;

import cn.lishiyuan.jaria2.client.enums.EventEnum;

public class DownloadErrorEvent extends BaseEvent{
    private static final EventEnum eventEnum = EventEnum.ERROR;

    public DownloadErrorEvent() {
        super(eventEnum);
    }
}
