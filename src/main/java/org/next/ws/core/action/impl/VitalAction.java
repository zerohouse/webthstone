package org.next.ws.core.action.impl;

import org.next.ws.core.action.AmountRepeatAction;
import org.next.ws.core.card.exception.CardUnUsableException;
import org.next.ws.core.fighter.Fighter;
import org.next.ws.core.game.player.Player;

import java.util.List;

public class VitalAction extends AmountRepeatAction {

    public VitalAction(String actionString) {
        super(actionString);
    }

    @Override
    public boolean able(Player player, List<Fighter> targetList) throws CardUnUsableException {
        if (targetList == null)
            throw new CardUnUsableException("타겟을 지정해야 합니다.");
        return true;
    }

    @Override
    public void act(Player player, List<Fighter> targetList) {
        for (int i = 0; i < repeat.getValue(); i++)
            massiveAttack(targetList);
    }

    private void massiveAttack(List<Fighter> targetList) {
        targetList.forEach(target -> target.addVital(amount.getValue()));
    }
}
