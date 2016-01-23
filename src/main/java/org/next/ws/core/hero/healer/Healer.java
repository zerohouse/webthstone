package org.next.ws.core.hero.healer;

import lombok.Getter;
import org.next.ws.core.hero.AbstractHero;
import org.next.ws.core.hero.WsHero;

@Getter
@WsHero("healer")
public class Healer extends AbstractHero {
    public Healer(String name, String img) {
        super(name, img, new HealerAbility());
    }
}
