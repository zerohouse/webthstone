package org.next.ws.core.hero.warrior;

import lombok.Getter;
import org.next.ws.core.game.Game;
import org.next.ws.core.hero.AbstractHero;

@Getter
public class Warrior extends AbstractHero {
    public Warrior(String name, String img, Game game) {
        super(name, img, new WarriorAbility(), game);
    }
}
