package org.next.ws.cards;

import lombok.Getter;
import org.next.ws.core.card.CardTemplate;

@Getter
public enum DefaultFighterCardTemplates {
    CARD(1, "세종"), CARD2(2, "망이"), CARD3(1, "광개토"), CARD4(1, "이순신"), CARD5(3, "그리메");

    DefaultFighterCardTemplates(Integer cost, String name) {
        template = new CardTemplate(cost, name);
    }

    private CardTemplate template;


}
