package org.next.ws.core.card;

import org.next.ws.core.fighter.FighterTemplate;

public interface CardTemplate {

    Integer getCost();

    String getImg();

    String getName();

    String getDesc();

    String getActionString();

    boolean isFighter();

    FighterTemplate getFighterTemplate();
}
