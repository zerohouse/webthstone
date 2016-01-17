package org.next.ws.core.card;

public class CardTemplate {

    private CardTemplate(Integer cost, String name, String desc, String img) {
        this.cost = cost;
        this.name = name;
        this.desc = desc;
        this.img = img;
    }

    public CardTemplate(Integer cost, String name, String desc, String img, String actionString) {
        this(cost, name, desc, img);
        this.fighter = false;
        this.actionString = actionString;
    }

    public CardTemplate(Integer cost, String name, String desc, String img, Integer attack, Integer vital) {
        this(cost, name, desc, img);
        this.fighter = true;
        this.attack = attack;
        this.vital = vital;
    }

    Integer cost;
    String name;
    String desc;
    String img;

    String actionString;

    boolean fighter;
    Integer attack;
    Integer vital;


}
