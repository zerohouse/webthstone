package org.next.ws.core.card;

import lombok.ToString;
import org.next.ws.core.card.template.CardTemplate;

@ToString
public class Card {

    private Integer cost;

    public Card(CardTemplate cardTemplate) {
        this.cost = cardTemplate.getCost();
    }
}
