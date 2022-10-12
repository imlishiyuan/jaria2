package com.bigbrotherlee.jaria2.client.event.process;

import com.bigbrotherlee.jaria2.client.event.Event;

@FunctionalInterface
public interface EventProcessor {
    void process(Event event);
}
