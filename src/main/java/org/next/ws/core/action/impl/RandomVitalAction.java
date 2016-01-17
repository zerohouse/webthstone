package org.next.ws.core.action.impl;

import org.next.ws.core.action.AmountRepeatAction;
import org.next.ws.core.card.exception.CardUnUsableException;
import org.next.ws.core.fighter.Fighter;
import org.next.ws.core.game.camp.Camp;

import java.util.List;
import java.util.Random;

public class RandomVitalAction extends AmountRepeatAction {

    private final Random random = new Random();

    public RandomVitalAction(String actionString) {
        super(actionString);
    }

    @Override
    public void ableCheck(Camp camp, List<Fighter> targetList) throws CardUnUsableException {
        if (targetList == null)
            throw new CardUnUsableException("타겟을 지정해야 합니다.");
    }

    @Override
    public void act(Camp camp, List<Fighter> targetList) {
        for (int i = 0; i < repeat.getValue(); i++)
            randAttack(targetList);
    }

    private void randAttack(List<Fighter> targetList) {
        int size = targetList.size();
        if (size == 0)
            return;
        Fighter target = targetList.get(random.nextInt(size));
        target.addVital(amount.getValue());
    }
}
