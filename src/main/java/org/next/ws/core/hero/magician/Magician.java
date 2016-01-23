package org.next.ws.core.hero.magician;

import lombok.Getter;
import org.next.ws.core.hero.AbstractHero;
import org.next.ws.core.hero.WsHero;

@Getter
@WsHero("magician")
public class Magician extends AbstractHero {
    public Magician(String name, String img) {
        super(name, img, new MagicianAbility());
    }
}
