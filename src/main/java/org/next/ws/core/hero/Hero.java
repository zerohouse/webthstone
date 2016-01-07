package org.next.ws.core.hero;

import org.next.ws.core.hero.prop.ability.Ability;
import org.next.ws.core.hero.prop.vital.Vital;

public interface Hero {
    String getName();

    Ability getAbility();

    Vital getVital();

}
