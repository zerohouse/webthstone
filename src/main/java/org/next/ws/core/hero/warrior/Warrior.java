package org.next.ws.core.hero.warrior;

import lombok.Getter;
import org.next.ws.core.hero.AbstractHero;
import org.next.ws.core.hero.WsHero;

@Getter
@WsHero("warrior")
public class Warrior extends AbstractHero {
    public Warrior(String name, String img) {
        super(name, img, new WarriorAbility());
    }
}
