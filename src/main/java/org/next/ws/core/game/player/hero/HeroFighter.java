package org.next.ws.core.game.player.hero;

import lombok.Getter;
import lombok.ToString;
import org.next.ws.core.fighter.Fighter;
import org.next.ws.core.hero.Hero;
import org.next.ws.core.hero.prop.ability.Ability;

@ToString
@Getter
public class HeroFighter extends Fighter {

    private Ability ability;
    private Mana mana;

    public HeroFighter(Hero hero) {
        super(hero.getAttackPower(), hero.getVital(), hero.getImg(), hero.getName());
        this.name = hero.getName();
        this.ability = hero.getAbility();
        this.mana = new Mana();
    }

    public HeroDto getHeroDto() {
        return new HeroDto(this);
    }

    @Override
    public void die() {
        player.lose();
    }

    public void startTurn() {
        mana.increaseMaxMana(1);
        mana.repair();
    }
}
