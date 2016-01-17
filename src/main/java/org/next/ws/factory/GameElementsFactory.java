package org.next.ws.factory;

import org.next.ws.core.action.Action;
import org.next.ws.core.card.Card;
import org.next.ws.core.card.CardTemplate;
import org.next.ws.core.fighter.FighterState;

import java.util.HashMap;
import java.util.Map;

public class GameElementsFactory {

    private static final Map<Integer, CardTemplate> CARD_TEMPLATE_MAP = new HashMap<>();

    private static final Map<Integer, FighterState> FIGHTER_TEMPLATE_MAP = new HashMap<>();

    private static final Map<Integer, Action> ACTION_MAP = new HashMap<>();

    private static Integer key = 1;

    static {
        push(DefaultFighterCardTemplates.CARD.getTemplate());
        push(DefaultFighterCardTemplates.CARD2.getTemplate());
        push(DefaultFighterCardTemplates.CARD3.getTemplate());
        push(DefaultFighterCardTemplates.CARD4.getTemplate());
        push(DefaultFighterCardTemplates.CARD5.getTemplate());
    }

    private static void push(CardTemplate card) {
        CARD_TEMPLATE_MAP.put(key++, card);
    }

    public static Card getNewCardByTemplateId(Integer id) {
        return new Card(CARD_TEMPLATE_MAP.get(id));
    }
}
