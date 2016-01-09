package org.next.ws.core.card;

import lombok.ToString;
import org.next.ws.core.action.Action;
import org.next.ws.core.card.property.Cost;
import org.next.ws.core.fighter.Fighter;
import org.next.ws.core.game.camp.Camp;

import java.util.ArrayList;
import java.util.List;

@ToString
public class Card {
    private Cost cost;
    private String name;
    private Action useAction;
    private List<Fighter> fighters;

    public Card(CardTemplate cardTemplate) {
        this.cost = new Cost(cardTemplate.getCost());
        this.name = cardTemplate.getName();
        this.fighters = new ArrayList<>();
    }

    public boolean usable(Camp camp) {
        return camp.getPlayingPlayer().hasEnoughMana(cost)
                && camp.addAble(fighters)
                && useAction.isActable();
    }

    public void use(Camp camp) {
        useAction.act();
        camp.addFighters(fighters);
    }
}
