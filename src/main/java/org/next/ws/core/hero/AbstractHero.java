package org.next.ws.core.hero;


import lombok.Getter;
import lombok.ToString;
import org.next.ws.core.hero.prop.ability.Ability;
import org.next.ws.core.hero.prop.vital.Vital;

@Getter
@ToString
public abstract class AbstractHero implements Hero {

    public AbstractHero(String name, Ability ability, Vital vital) {
        this.name = name;
        this.ability = ability;
        this.vital = vital;
    }

    protected String name;
    protected Ability ability;
    protected Vital vital;
}
