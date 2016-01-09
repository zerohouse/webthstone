package org.next.ws.core.game.player.hero;

import lombok.Getter;
import lombok.ToString;
import org.next.ws.core.fighter.Fighter;
import org.next.ws.core.fighter.property.Vital;
import org.next.ws.core.hero.Hero;
import org.next.ws.core.hero.prop.ability.Ability;

@ToString
@Getter
public class GameHero extends Fighter {

    private String name;
    private Ability ability;
    private Vital vital;
    private Mana mana;

    public GameHero(Hero hero) {
        super(hero.getAttackPower(), hero.getVital());
        this.name = hero.getName();
        this.ability = hero.getAbility();
        this.vital = hero.getVital();
        this.mana = new Mana();
    }

    public void manaAdd(int size) {
        mana.add(size);
    }

}
