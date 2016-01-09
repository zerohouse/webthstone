package org.next.ws.core.hero.magician;

import lombok.Getter;
import org.next.ws.core.game.Game;
import org.next.ws.core.hero.AbstractHero;

@Getter
public class Magician extends AbstractHero {
    public Magician(String name, Game game) {
        super(name, new MagicianAbility(), game);
    }
}
