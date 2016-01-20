package org.next.ws.core.card;

import lombok.Getter;
import lombok.Setter;
import org.next.ws.core.fighter.FighterDto;

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
    private FighterDto fighter;

    public CardDto(Card card) {
        this.id = card.cardIdInGame;
        this.cost = card.cost.getCost();
        this.title = card.name;
        this.desc = card.desc;
        this.img = card.img;
        if (card.fighter == null)
            return;
        this.fighter = new FighterDto(card.fighter);
    }
}
