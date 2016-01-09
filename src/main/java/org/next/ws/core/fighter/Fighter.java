package org.next.ws.core.fighter;

import lombok.Setter;
import org.next.ws.core.action.Action;
import org.next.ws.core.fighter.property.*;

@Setter
public abstract class Fighter {

    protected final AttackPower attackPower;
    protected final Vital vital;
    protected MagicPower magicPower;
    protected Species species;
    protected FighterState fighterState;
    protected Action deathAction;
    protected Action birthAction;
    protected Action turnStartAction;
    protected Action turnEndAction;
    protected Action AttackAction;
    protected Action HeatedAction;

    protected Fighter(AttackPower attackPower, Vital vital) {
        this.attackPower = attackPower;
        this.vital = vital;
    }

    public void addVital(int amount) {
        vital.add(amount);
        if (!vital.isAlive())
            deathAction.act();
    }

    public void die() {

    }
}
