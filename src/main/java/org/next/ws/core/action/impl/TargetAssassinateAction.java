package org.next.ws.core.action.impl;

import org.next.ws.core.action.SimpleAction;
import org.next.ws.core.card.exception.CardUnUsableException;
import org.next.ws.core.fighter.Fighter;
import org.next.ws.core.game.player.Player;

import java.util.List;

public class TargetAssassinateAction extends SimpleAction {

    @Override
    public boolean able(Player player, List<Fighter> targetList) throws CardUnUsableException {
        if (targetList == null)
            throw new CardUnUsableException("타겟을 지정해야 합니다.");
        return  true;
    }

    @Override
    public void act(Player player, List<Fighter> targetList) {
        targetList.forEach(Fighter::die);
    }

}
