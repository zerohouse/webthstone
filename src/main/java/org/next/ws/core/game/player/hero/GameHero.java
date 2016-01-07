package org.next.ws.core.game.player.hero;

import lombok.ToString;
import org.next.ws.core.hero.prop.ability.Ability;
import org.next.ws.core.hero.prop.vital.Vital;
import org.next.ws.core.hero.Hero;

@ToString
public class GameHero {

    private String name;
    private Ability ability;
    private Vital vital;
    private Mana mana;

    public GameHero(Hero hero) {
        this.name = hero.getName();
        this.ability = hero.getAbility();
        this.vital = hero.getVital();
        this.mana = new Mana();
    }
}
