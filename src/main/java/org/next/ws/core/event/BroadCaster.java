package org.next.ws.core.event;

import org.next.ws.core.event.standard.EventResult;
import org.next.ws.core.event.standard.EventType;

public interface BroadCaster {
    void broadCast(EventType type, EventResult eventResult);
}
