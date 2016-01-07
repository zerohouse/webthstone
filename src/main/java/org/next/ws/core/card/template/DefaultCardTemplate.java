package org.next.ws.core.card.template;

import lombok.Getter;

@Getter
public enum DefaultCardTemplate implements CardTemplate {
    CARD(1, "세종"),CARD2(1, "광개토"),CARD3(1, "이순신"),CARD4(1, "망이"),CARD5(1, "만적");

    private final Integer cost;
    private final String name;

    DefaultCardTemplate(Integer cost, String name) {
        this.cost = cost;
        this.name = name;
    }
}
