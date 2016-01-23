package org.next.ws.web.card;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.next.ws.web.action.ActionEntity;
import org.next.ws.web.action.ActionEntityDto;
import org.next.ws.web.deck.DeckHasCard;
import org.next.ws.web.fighter.FighterEntity;
import org.next.ws.web.fighter.FighterEntityDto;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CardEntityDto {

    private FighterEntityDto fighter;

    private ActionEntityDto action;

    private Long id;

    private Integer cost;

    private String img;

    private String name;

    private String desc;

    private boolean fighterCard;

    public CardEntityDto(CardEntity cardEntity) {
        this.id = cardEntity.getId();
        this.cost = cardEntity.getCost();
        this.img = cardEntity.getImg();
        this.name = cardEntity.getName();
        this.desc = cardEntity.getDesc();
        this.fighterCard = cardEntity.isFighterCard();
        if (cardEntity.getFighterTemplate() != null)
            this.fighter = new FighterEntityDto(cardEntity.getFighterTemplate());
        if (cardEntity.getActionTemplate() != null)
            this.action = new ActionEntityDto(cardEntity.getActionTemplate());

    }
}
