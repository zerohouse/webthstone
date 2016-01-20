package org.next.ws.core.action;

import org.next.ws.core.card.exception.CardUnUsableException;
import org.next.ws.core.fighter.Fighter;
import org.next.ws.core.game.camp.Camp;

import java.util.List;

public abstract class NonTargetAction implements Action {

    public abstract void ableCheck(Camp camp);

    public abstract void act(Camp camp);

    @Override
    public void ableCheck(Camp camp, List<Fighter> targetList) throws CardUnUsableException {
        ableCheck(camp);
    }

    @Override
    public void act(Camp camp, List<Fighter> targetList) {
        act(camp);
    }
}
