package org.next.ws.core.action.impl;

import org.next.ws.core.action.SimpleAction;
import org.next.ws.core.card.exception.CardUnUsableException;
import org.next.ws.core.fighter.Fighter;
import org.next.ws.core.game.camp.Camp;

import java.util.List;

public class TargetAssassinateAction extends SimpleAction {

    @Override
    public void ableCheck(Camp camp, List<Fighter> targetList) throws CardUnUsableException {
        if (targetList == null)
            throw new CardUnUsableException("타겟을 지정해야 합니다.");
    }

    @Override
    public void act(Camp camp, List<Fighter> targetList) {
        targetList.forEach(Fighter::die);
    }

}
