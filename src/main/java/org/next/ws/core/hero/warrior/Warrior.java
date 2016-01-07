package org.next.ws.core.hero.warrior;

import lombok.Getter;
import lombok.ToString;
import org.next.ws.core.hero.AbstractHero;
import org.next.ws.core.hero.magician.MagicianAbility;
import org.next.ws.core.hero.prop.ability.Ability;
import org.next.ws.core.hero.prop.vital.DefaultHeroVital;
import org.next.ws.core.hero.prop.vital.Vital;
import org.next.ws.core.hero.Hero;

@Getter
public class Warrior extends AbstractHero {
    public Warrior(String name) {
        super(name, new WarriorAbility(), new DefaultHeroVital());
    }
}
