package org.next.ws.core.game.event;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventInfo {
    private Object info;
    public EventInfo(Object info) {
        this.info = info;
    }
}
