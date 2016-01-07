package org.next.ws.core.hero.magician;

import lombok.Getter;
import lombok.ToString;
import org.next.ws.core.hero.AbstractHero;
import org.next.ws.core.hero.hunter.HunterAbility;
import org.next.ws.core.hero.prop.ability.Ability;
import org.next.ws.core.hero.prop.vital.DefaultHeroVital;
import org.next.ws.core.hero.prop.vital.Vital;
import org.next.ws.core.hero.Hero;

@Getter
public class Magician extends AbstractHero {
    public Magician(String name) {
        super(name, new MagicianAbility(), new DefaultHeroVital());
    }
}
