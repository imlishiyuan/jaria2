package cn.lishiyuan.jaria2.client.event;

import cn.lishiyuan.jaria2.client.enums.EventEnum;

public class DownloadPauseEvent extends BaseEvent{

    private static final EventEnum eventEnum = EventEnum.PAUSE;

    public DownloadPauseEvent() {
        super(eventEnum);
    }
}
