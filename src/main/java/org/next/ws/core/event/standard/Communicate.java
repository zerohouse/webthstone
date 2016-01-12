package org.next.ws.core.event.standard;

import lombok.Getter;

@Getter
public class Communicate {
    private EventType eventType;
    private Object body;
}
