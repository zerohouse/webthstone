package org.next.ws.factory;

import lombok.Getter;
import org.next.ws.core.card.CardTemplate;
import org.next.ws.core.card.SimpleCardTemplate;

@Getter
public enum DefaultFighterCardTemplates {
    CARD(1, "세종", "img", "desc", 1, 3),
    CARD2(2, "세종", "img", "desc", 1, 3),
    CARD3(3, "세종", "img", "desc", 1, 3),
    CARD4(4, "세종", "img", "desc", 1, 3),
    CARD5(5, "세종", "img", "desc", 1, 3),
    CARD6(6, "세종", "img", "desc", 1, 3);


    private CardTemplate template;


    DefaultFighterCardTemplates(int cost, String name, String img, String desc, int i1, int i2) {
        template = new SimpleCardTemplate(cost, name, img, desc, i1, i2);
    }
}
