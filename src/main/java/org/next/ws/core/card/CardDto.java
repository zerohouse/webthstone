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
    private String detail;
    private String img;
    private int id;
    private FighterTemplateDto fighter;
    private boolean fighterCard;

    public CardDto(Card card) {
        this.id = card.cardIdInGame;
        this.cost = card.cost.getCost();
        this.name = card.name;
        this.detail = card.detail;
        this.img = card.img;
        this.fighterCard = card.fighterCard;
        if (card.fighterTemplate == null)
            return;
        this.fighter = new FighterTemplateDto(card.fighterTemplate);
    }
}
