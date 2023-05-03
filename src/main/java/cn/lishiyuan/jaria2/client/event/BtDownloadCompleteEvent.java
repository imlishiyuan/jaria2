package cn.lishiyuan.jaria2.client.event;

import cn.lishiyuan.jaria2.client.enums.EventEnum;

public class BtDownloadCompleteEvent extends BaseEvent{
    private static final EventEnum eventEnum = EventEnum.BT_COMPLETE;

    public BtDownloadCompleteEvent() {
        super(eventEnum);
    }
}
