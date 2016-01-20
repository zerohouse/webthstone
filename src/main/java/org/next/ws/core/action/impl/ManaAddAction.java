package org.next.ws.core.action.impl;

import org.next.ws.core.action.AmountAction;
import org.next.ws.core.fighter.Fighter;
import org.next.ws.core.game.player.Player;

import java.util.List;

public class ManaAddAction extends AmountAction {

    public ManaAddAction(String amount) {
        super(amount);
    }

    @Override
    public boolean able(Player player, List<Fighter> targetList) {
        return  true;
    }

    @Override
    public void act(Player player, List<Fighter> targetList) {
        player.getGameHero().getMana().add(amount.getValue());
    }
}
