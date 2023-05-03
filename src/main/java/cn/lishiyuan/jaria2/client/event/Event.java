package cn.lishiyuan.jaria2.client.event;

import cn.lishiyuan.jaria2.client.enums.EventEnum;
import com.alibaba.fastjson.JSON;

public abstract class Event {
    private final EventEnum eventEnum;
    protected Event(EventEnum eventEnum){
        this.eventEnum = eventEnum;
    }

    public EventEnum getEventName() {
        return eventEnum;
    }

    public String toJsonString(){
        return JSON.toJSONString(this);
    }

    public static class EventContent{
        private String gid;

        public String getGid() {
            return gid;
        }

        public void setGid(String gid) {
            this.gid = gid;
        }
    }
}
