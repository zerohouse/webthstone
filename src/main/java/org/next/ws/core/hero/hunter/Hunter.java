package org.next.ws.core.hero.hunter;

import lombok.Getter;
import org.next.ws.core.hero.AbstractHero;
import org.next.ws.core.hero.WsHero;

@Getter
@WsHero("hunter")
public class Hunter extends AbstractHero {
    public Hunter(String name, String img) {
        super(name, img, new HunterAbility());
    }
}
