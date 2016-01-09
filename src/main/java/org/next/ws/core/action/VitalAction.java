package org.next.ws.core.action;

import org.next.ws.core.fighter.Fighter;
import org.next.ws.core.type.RangeAble;

import java.util.ArrayList;
import java.util.List;

public class VitalAction implements Action {

    private final RangeAble repeat;
    private final RangeAble amount;
    private List<Fighter> targetList;

    VitalAction(RangeAble repeat, RangeAble amount, List<Fighter> targetList) {
        this.repeat = repeat;
        this.amount = amount;
        this.targetList = targetList;
    }

    VitalAction(RangeAble repeat, RangeAble amount, Fighter target) {
        this.repeat = repeat;
        this.amount = amount;
        this.targetList = new ArrayList<>();
        targetList.add(target);
    }

    @Override
    public boolean isActable() {
        return true;
    }

    @Override
    public void act() {
        for (int i = 0; i < repeat.getValue(); i++)
            massiveAttack();
    }

    private void massiveAttack() {
        targetList.forEach(target -> target.addVital(amount.getValue()));
    }
}
