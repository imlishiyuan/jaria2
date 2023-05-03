package cn.lishiyuan.jaria2.client.event.process;

import cn.lishiyuan.jaria2.client.event.Event;

@FunctionalInterface
public interface EventProcessor {
    void process(Event event);
}
