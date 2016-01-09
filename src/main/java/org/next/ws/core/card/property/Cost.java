package org.next.ws.core.card.property;

import lombok.Getter;

@Getter
public class Cost {

    private int cost;
    private int originCost;

    public Cost(int cost) {
        this.cost = cost;
        this.originCost = cost;
    }

}
