package org.next.ws.core.action.serialize;

import org.next.ws.core.game.event.EventType;

public interface ActionTemplate {

    String getTemplateId();

    EventType getTiming();

    Object[] getParams();

}
