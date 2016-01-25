package org.next.ws.web.card;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.next.ws.web.action.ActionEntityDto;
import org.next.ws.web.deck.DeckHasCard;
import org.next.ws.web.fighter.FighterEntityDto;

@Getter
@Setter
@NoArgsConstructor
public class CardEntityDto {

    private FighterEntityDto fighter;

    private ActionEntityDto action;

    private Long id;

    private Long deckHasCardId;

    private Integer cost;

    private String img;

    private String name;

    private String detail;

    private boolean fighterCard;


    public CardEntityDto(DeckHasCard deckHasCard) {
        this(deckHasCard.getCard());
        this.deckHasCardId = deckHasCard.getId();
    }

    public CardEntityDto(CardEntity cardEntity) {
        this.id = cardEntity.getId();
        this.cost = cardEntity.getCost();
        this.img = cardEntity.getImg();
        this.name = cardEntity.getName();
        this.detail = cardEntity.getDetail();
        this.fighterCard = cardEntity.isFighterCard();
        if (cardEntity.getFighterTemplate() != null)
            this.fighter = new FighterEntityDto(cardEntity.getFighterTemplate());
        if (cardEntity.getActionTemplate() != null)
            this.action = new ActionEntityDto(cardEntity.getActionTemplate());
    }
}
