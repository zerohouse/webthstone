package org.next.ws.core.game.event;

import lombok.Getter;

@Getter
public class Event {

    private EventType type;
    private EventInfo info;

    public Event(EventType type) {
        this.type = type;
    }

    public Event(EventType type, EventInfo info) {
        this.type = type;
        this.info = info;
    }
}
