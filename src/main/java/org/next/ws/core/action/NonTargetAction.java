package org.next.ws.core.action;

import org.next.ws.core.card.exception.CardUnUsableException;
import org.next.ws.core.fighter.Fighter;
import org.next.ws.core.game.player.Player;

import java.util.List;

public abstract class NonTargetAction implements Action {

    public abstract boolean able(Player player);

    public abstract void act(Player player);

    public boolean able(Player player, List<Fighter> targetList) throws CardUnUsableException {
        return able(player);
    }

    @Override
    public void act(Player player, List<Fighter> targetList) {
        act(player);
    }
}
