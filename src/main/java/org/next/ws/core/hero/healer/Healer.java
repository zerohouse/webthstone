package org.next.ws.core.hero.healer;

import lombok.Getter;
import lombok.ToString;
import org.next.ws.core.hero.AbstractHero;
import org.next.ws.core.hero.prop.vital.DefaultHeroVital;

@Getter
public class Healer extends AbstractHero {
    public Healer(String name) {
        super(name, new HealerAbility(), new DefaultHeroVital());
    }
}
