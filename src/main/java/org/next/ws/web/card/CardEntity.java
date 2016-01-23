package org.next.ws.web.card;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;
import org.next.ws.core.action.serialize.ActionTemplate;
import org.next.ws.core.card.CardTemplate;
import org.next.ws.web.action.ActionEntity;
import org.next.ws.web.deck.DeckHasCard;
import org.next.ws.web.fighter.FighterEntity;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class CardEntity implements CardTemplate {


    public CardEntity(CardParameterDto card) {
        cost = card.getCost();
        name = card.getName();
        desc = card.getDesc();
        img = card.getImg();
        fighterCard = card.isFighterCard();
        if(fighterCard){
            fighterTemplate = new FighterEntity(card.getFighter(), this);
            return;
        }
        actionTemplate = new ActionEntity(card.getEffect());
    }


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    FighterEntity fighterTemplate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private ActionEntity actionTemplate;

    @OneToMany(mappedBy = "card")
    private List<DeckHasCard> deckHasCardList;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Integer cost;

    @Column
    private String img;

    @Column
    private String name;

    @Column
    private String desc;

    @Type(type = "org.hibernate.type.NumericBooleanType")
    @Column
    private boolean fighterCard;

    @Override
    public ActionTemplate getUseActionTemplate() {
        return actionTemplate;
    }
}
