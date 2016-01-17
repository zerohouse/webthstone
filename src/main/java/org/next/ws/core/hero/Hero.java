package org.next.ws.core.hero;

import org.next.ws.core.fighter.property.AttackPower;
import org.next.ws.core.fighter.property.MagicPower;
import org.next.ws.core.fighter.property.Vital;
import org.next.ws.core.hero.prop.ability.Ability;

public interface Hero {
    String getName();

    String getImg();

    Ability getAbility();

    Vital getVital();

    AttackPower getAttackPower();

    MagicPower getMagicPower();

}
