package org.next.ws.core.card;

import lombok.Getter;

@Getter
public class CardTemplate {

    public CardTemplate(Integer cost, String name) {
        this.cost = cost;
        this.name = name;
    }

    private Integer cost;
    private String name;
    private String desc;
    private String img;
}
