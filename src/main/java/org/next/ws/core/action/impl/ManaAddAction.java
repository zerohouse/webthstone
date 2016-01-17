package org.next.ws.core.action.impl;

import org.next.ws.core.action.AmountAction;
import org.next.ws.core.fighter.Fighter;
import org.next.ws.core.game.camp.Camp;

import java.util.List;

public class ManaAddAction extends AmountAction {

    public ManaAddAction(String amount) {
        super(amount);
    }

    @Override
    public void ableCheck(Camp camp, List<Fighter> targetList) {
    }

    @Override
    public void act(Camp camp, List<Fighter> targetList) {
        camp.getPlayingPlayer().getGameHero().getMana().add(amount.getValue());
    }
}
