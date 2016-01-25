package org.next.ws.core.card;

import org.next.ws.core.action.serialize.ActionTemplate;
import org.next.ws.core.fighter.FighterTemplate;

public interface CardTemplate {

    Integer getCost();

    String getImg();

    String getName();

    String getDetail();

    boolean isFighterCard();

    FighterTemplate getFighterTemplate();

    ActionTemplate getUseActionTemplate();

}
