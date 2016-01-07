package org.next.ws.core.card.template;

import lombok.Getter;

@Getter
public enum DefaultCardTemplate implements CardTemplate {
    CARD(1);

    private final Integer cost;

    DefaultCardTemplate(Integer cost) {
        this.cost = cost;
    }
}
