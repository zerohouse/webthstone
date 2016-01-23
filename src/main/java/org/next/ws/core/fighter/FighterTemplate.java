package org.next.ws.core.fighter;

import org.next.ws.core.action.serialize.ActionTemplate;

import java.util.List;

public interface FighterTemplate {
    String getName();

    String getImg();

    Integer getVital();

    Integer getAttack();

    List<ActionTemplate> getActionList();
}
