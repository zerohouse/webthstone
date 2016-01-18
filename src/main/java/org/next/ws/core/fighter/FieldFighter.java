package org.next.ws.core.fighter;

import org.next.ws.core.fighter.property.AttackPower;
import org.next.ws.core.fighter.property.Vital;

public class FieldFighter extends Fighter {
    public FieldFighter(AttackPower attackPower, Vital vital, String img, String name) {
        super(attackPower, vital, img, name);
    }

    @Override
    public void die() {
        camp.removeFighter(this);
        deathAction.act(camp, null);
    }
}
