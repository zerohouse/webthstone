package org.next.ws.core.hero;


import lombok.Getter;
import lombok.ToString;
import org.next.ws.core.action.Action;
import org.next.ws.core.fighter.property.AttackPower;
import org.next.ws.core.fighter.property.MagicPower;
import org.next.ws.core.fighter.property.Vital;
import org.next.ws.core.game.Game;
import org.next.ws.core.hero.prop.ability.Ability;

@Getter
@ToString
public abstract class AbstractHero implements Hero {

    public AbstractHero(String name, Ability ability, Game game) {
        this.name = name;
        this.ability = ability;
        this.vital = Vital.getDefaultHeroVital();
        this.attackPower = AttackPower.getDefaultAttackPower();
        this.magicPower = MagicPower.getDefaultMagicPower();
        this.deathAction = new Action() {
            @Override
            public boolean isActable() {
                return true;
            }

            @Override
            public void act() {
                game.end();
            }
        };

    }

    protected String name;
    protected Ability ability;
    protected Vital vital;
    protected Action deathAction;
    protected AttackPower attackPower;
    protected MagicPower magicPower;
}
