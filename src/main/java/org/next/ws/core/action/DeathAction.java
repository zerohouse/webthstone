package org.next.ws.core.action;

import org.next.ws.core.fighter.Fighter;
import org.next.ws.core.type.RangeAble;

import java.util.ArrayList;
import java.util.List;

public class DeathAction implements Action {

    private List<Fighter> targetList;

    DeathAction(List<Fighter> targetList) {
        this.targetList = targetList;
    }

    DeathAction(Fighter target) {
        this.targetList = new ArrayList<>();
        targetList.add(target);
    }

    @Override
    public boolean isActable() {
        return true;
    }

    @Override
    public void act() {
        targetList.forEach(fighter -> fighter.die());
    }

}
