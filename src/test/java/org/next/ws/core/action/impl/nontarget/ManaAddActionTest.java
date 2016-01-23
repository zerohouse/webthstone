package org.next.ws.core.action.impl.nontarget;

import org.junit.Test;
import org.next.ws.core.action.serialize.ActionTemplate;
import org.next.ws.core.game.event.EventType;
import org.next.ws.core.scanner.ComponentScanner;

public class ManaAddActionTest {

    @Test
    public void mana() {
        ComponentScanner.getAction(new ActionTemplate() {
            @Override
            public String getTemplateId() {
                return "manaadd_rand";
            }

            @Override
            public EventType getTiming() {
                return null;
            }

            @Override
            public Object[] getParams() {
                return "1,2".split(",");
            }
        });

    }
}