package org.next.ws.core.card;

import lombok.Getter;
import lombok.Setter;
import org.next.ws.core.fighter.FighterTemplateDto;

@Getter
@Setter
public class CardDto {
    private int cost;
    private int attack;
    private int vital;
    private String name;
    private String desc;
    private String img;
    private int id;
    private FighterTemplateDto fighter;

    public CardDto(Card card) {
        this.id = card.cardIdInGame;
        this.cost = card.cost.getCost();
        this.name = card.name;
        this.desc = card.desc;
        this.img = card.img;
        if (card.fighterTemplate == null)
            return;
        this.fighter = new FighterTemplateDto(card.fighterTemplate);
    }
}
