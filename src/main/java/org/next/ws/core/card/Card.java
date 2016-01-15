package org.next.ws.core.card;

import lombok.Getter;
import lombok.ToString;
import org.next.ws.core.action.Action;
import org.next.ws.core.card.property.Cost;
import org.next.ws.core.fighter.Fighter;
import org.next.ws.core.game.camp.Camp;

import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
public class Card {
    private Cost cost;
    private String name;
    private Action useAction;
    private List<Fighter> fighters;
    private String desc;
    private String img;

    public Card(CardTemplate cardTemplate) {
        this.cost = new Cost(cardTemplate.getCost());
        this.name = cardTemplate.getName();
        this.desc = cardTemplate.getDesc();
        this.img = cardTemplate.getImg();
        this.fighters = new ArrayList<>();
    }

    public boolean usable(Camp camp) {
        return camp.getPlayingPlayer().hasEnoughMana(cost)
                && camp.getPlayingPlayer().addAble(fighters)
                && useAction.isActable();
    }

    public void use(Camp camp) {
        useAction.act();
        camp.getPlayingPlayer().addFighters(fighters);
    }


}
