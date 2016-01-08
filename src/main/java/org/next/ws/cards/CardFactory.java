package org.next.ws.cards;

import org.next.ws.core.card.Card;
import org.next.ws.core.card.CardTemplate;

import java.util.HashMap;
import java.util.Map;

public class CardFactory {

    private static final Map<Integer, CardTemplate> CARD_TEMPLATE_MAP;

    private static Integer key = 1;

    static {
        CARD_TEMPLATE_MAP = new HashMap<>();
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
