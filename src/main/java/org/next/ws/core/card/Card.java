package org.next.ws.core.card;

import lombok.ToString;
import org.next.ws.core.fighter.Fighter;
import org.next.ws.core.game.camp.Camp;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

@ToString
public class Card {

    private Integer cost;
    private String name;
    private Predicate<Camp> usableCheck;
    private Consumer<Camp> useAction;
    private List<Fighter> fighters;

    public Card(CardTemplate cardTemplate) {
        this.cost = cardTemplate.getCost();
        this.name = cardTemplate.getName();
        this.fighters = new ArrayList<>();
    }

    public boolean usable(Camp camp) {
        return camp.addAble(fighters) && usableCheck.test(camp);
    }

    public void use(Camp camp) {
        camp.addFighters(fighters);
        useAction.accept(camp);
    }
}
