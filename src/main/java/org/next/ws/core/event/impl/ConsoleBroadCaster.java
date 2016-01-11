package org.next.ws.core.event.impl;

import org.next.ws.core.event.BroadCaster;
import org.next.ws.core.event.standard.EventResult;
import org.next.ws.core.event.standard.EventType;

public class ConsoleBroadCaster implements BroadCaster {
    @Override
    public void broadCast(EventType type, EventResult eventResult) {
        System.out.println(type);
        System.out.println(eventResult);
    }
}
