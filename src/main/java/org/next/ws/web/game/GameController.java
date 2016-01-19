package org.next.ws.web.game;

import org.next.ws.core.event.standard.GameEventType;
import org.next.ws.core.fighter.Fighter;
import org.next.ws.core.game.player.Player;
import org.next.ws.web.jeo.JeoController;
import org.next.ws.web.jeo.JeoEvent;
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
        assureNotNull(user.getPlayer()).getCamp().endTurn();
    }

    @JeoEvent("game.fighter_attack")
    public void attack(Player player, Integer by, Integer target){
        Fighter attacker = player.getCamp().getFighterById(by);
        Fighter defender = player.getCamp().getFighterById(target);
        attacker.attack(defender);
        player.getCamp().getGame().gameStateUpdate();
        player.getCamp().getGame().broadCast(GameEventType.ATTACK, "공격 : " + attacker.getName() + " -> " + defender.getName());
    }
}
