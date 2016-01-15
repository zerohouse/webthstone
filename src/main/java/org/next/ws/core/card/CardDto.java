package org.next.ws.core.card;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardDto {
    private int cost;
    private int attack;
    private int vital;
    private String title;
    private String desc;
    private String img;

    public CardDto(Card card) {

        this.cost = card.getCost().getCost();
        this.title = card.getName();
        this.desc = card.getDesc();
        this.img = card.getImg();
        if (card.getFighters().size() == 0)
            return;
        this.attack = card.getFighters().get(0).getAttackPower().getPower();
        this.vital = card.getFighters().get(0).getVital().getVital();

    }
}
