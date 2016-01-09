package org.next.ws.core.action;

import org.next.ws.core.fighter.Fighter;
import org.next.ws.core.type.RangeAble;

import java.util.List;
import java.util.Random;

public class RandomVitalAction implements Action {


    private final Random random;
    private final RangeAble repeat;
    private final RangeAble amount;
    private final List<Fighter> targetList;


    RandomVitalAction(RangeAble repeat, RangeAble amount, List<Fighter> targetList) {
        random = new Random();
        this.repeat = repeat;
        this.amount = amount;
        this.targetList = targetList;
    }

    @Override
    public boolean isActable() {
        return true;
    }

    @Override
    public void act() {
        for (int i = 0; i < repeat.getValue(); i++)
            randAttack();
    }

    private void randAttack() {
        int size = targetList.size();
        if (size == 0)
            return;
        Fighter target = targetList.get(random.nextInt(size));
        target.addVital(amount.getValue());
    }
}
