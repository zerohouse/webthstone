package org.next.ws.core.hero.hunter;

import lombok.Getter;
import org.next.ws.core.game.Game;
import org.next.ws.core.hero.AbstractHero;

@Getter
public class Hunter extends AbstractHero {
    public Hunter(String name, Game game) {
        super(name, new HunterAbility(), game);
    }
}
