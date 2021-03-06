package org.next.ws.core.hero;


import lombok.Getter;
import lombok.ToString;
import org.next.ws.core.fighter.property.AttackPower;
import org.next.ws.core.fighter.property.MagicPower;
import org.next.ws.core.fighter.property.Vital;
import org.next.ws.core.hero.prop.ability.Ability;

@Getter
@ToString
public abstract class AbstractHero implements Hero {

    public AbstractHero(String name, String img, Ability ability) {
        this.name = name;
        this.img = img;
        this.ability = ability;
        this.vital = Vital.getDefaultHeroVital();
        this.attackPower = AttackPower.getDefaultAttackPower();
        this.magicPower = MagicPower.getDefaultMagicPower();
    }

    protected String img;
    protected String name;
    protected Ability ability;
    protected Vital vital;
    protected AttackPower attackPower;
    protected MagicPower magicPower;
}
