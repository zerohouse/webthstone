package org.next.ws.web.game;

import org.next.ws.core.game.player.Player;
import org.next.ws.jeo.JeoController;
import org.next.ws.jeo.JeoEvent;
import org.next.ws.web.user.User;

import java.util.List;

import static org.next.ws.util.Util.assureNotNull;

@JeoController
public class GameController {

    @JeoEvent("card.submit")
    public void gamePlay(Player player, Integer id, List<Integer> targetList) {
        player.useCard(id, player.resolveTargetList(targetList));
    }


    @JeoEvent("game.end_turn")
    public void endTurn(User user){
        assureNotNull(user.getPlayer()).endTurn();
    }

    @JeoEvent("game.fighter_attack")
    public void attack(Player player, Integer by, Integer target){
        player.attack(by, target);
    }
}
