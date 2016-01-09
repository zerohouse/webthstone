package org.next.ws.core.hero.healer;

import lombok.Getter;
import org.next.ws.core.game.Game;
import org.next.ws.core.hero.AbstractHero;

@Getter
public class Healer extends AbstractHero {
    public Healer(String name, Game game) {
        super(name, new HealerAbility(), game);
    }
}
