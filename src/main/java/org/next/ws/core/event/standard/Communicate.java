package org.next.ws.core.event.standard;

import lombok.Getter;

@Getter
public class Communicate {
    private GameEventType gameEventType;
    private Object body;
}
