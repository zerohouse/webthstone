package org.next.ws.core.action.serialize;

import lombok.Getter;
import org.next.ws.core.game.event.EventType;

@Getter
public class SimpleActionTemplate implements ActionTemplate {

    private String templateId;
    private EventType timing;
    private Object[] params;

    public SimpleActionTemplate(String templateId, EventType timing, Object... params) {
        this.templateId = templateId;
        this.timing = timing;
        this.params = params;
    }
}
