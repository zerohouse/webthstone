package org.next.ws.core.card.factory;

import org.next.ws.core.card.Card;
import org.next.ws.core.card.template.CardTemplate;
import org.next.ws.core.card.template.DefaultCardTemplate;

import java.util.HashMap;
import java.util.Map;

public class cardFactory {


    private static final Map<Integer, CardTemplate> CARD_MAP;

    static {
        CARD_MAP = new HashMap<>();
        CARD_MAP.put(1, DefaultCardTemplate.CARD);
        CARD_MAP.put(2, DefaultCardTemplate.CARD);
        CARD_MAP.put(3, DefaultCardTemplate.CARD);
        CARD_MAP.put(4, DefaultCardTemplate.CARD);
        CARD_MAP.put(5, DefaultCardTemplate.CARD);
    }


    public static Card getNewCardById(Integer integer) {
        return new Card(CARD_MAP.get(integer));
    }
}
