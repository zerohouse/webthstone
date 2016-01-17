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
    private int id;

    public CardDto(Card card) {
        this.id = card.cardIdInGame;
        this.cost = card.cost.getCost();
        this.title = card.name;
        this.desc = card.desc;
        this.img = card.img;
        if (card.fighter == null)
            return;
        this.attack = card.fighter.getAttackPower().getPower();
        this.vital = card.fighter.getVital().getVital();
    }
}
