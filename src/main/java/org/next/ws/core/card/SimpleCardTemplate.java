package org.next.ws.core.card;

import lombok.Getter;
import org.next.ws.core.action.Action;
import org.next.ws.core.fighter.FighterTemplate;
import org.next.ws.core.fighter.SimpleFighterTemplate;

@Getter
public class SimpleCardTemplate implements CardTemplate {

    SimpleCardTemplate(Integer cost, String name, String desc, String img) {
        this.cost = cost;
        this.name = name;
        this.desc = desc;
        this.img = img;
    }

    public SimpleCardTemplate(Integer cost, String name, String desc, String img, String actionString) {
        this(cost, name, desc, img);
        this.fighter = false;
        this.actionString = actionString;
    }

    public SimpleCardTemplate(Integer cost, String name, String desc, String img, Integer attack, Integer vital) {
        this(cost, name, desc, img);
        this.fighter = true;
        this.attack = attack;
        this.vital = vital;
        this.fighterTemplate = new SimpleFighterTemplate(name, img, attack, vital);
    }

    Integer cost;
    String name;
    String desc;
    String img;

    String actionString;

    boolean fighter;
    Integer attack;
    Integer vital;

    FighterTemplate fighterTemplate;

}
