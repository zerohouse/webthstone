package org.next.ws.core.hero.hunter;

import lombok.Getter;
import org.next.ws.core.hero.healer.HealerAbility;
import org.next.ws.core.hero.prop.ability.Ability;
import org.next.ws.core.hero.AbstractHero;
import org.next.ws.core.hero.prop.vital.DefaultHeroVital;
import org.next.ws.core.hero.prop.vital.Vital;

@Getter
public class Hunter extends AbstractHero {
    public Hunter(String name) {
        super(name, new HunterAbility(), new DefaultHeroVital());
    }
}
