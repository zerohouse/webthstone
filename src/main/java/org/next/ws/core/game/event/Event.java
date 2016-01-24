package org.next.ws.core.game.event;

import lombok.Getter;

@Getter
public class Event {

    private EventType type;
    private Object result;

    public Event(EventType type) {
        this.type = type;
    }

    public Event(EventType type, Object result) {
        this.type = type;
        this.result = result;
    }
}
