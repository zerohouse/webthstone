package org.next.ws.web.game;

import org.next.ws.core.event.standard.GameEventType;
import org.next.ws.core.fighter.Fighter;
import org.next.ws.core.game.player.Player;
import org.next.ws.web.jeo.JeoController;
import org.next.ws.web.jeo.JeoEvent;
import org.next.ws.web.jeo.user.MyTurnOnly;
import org.next.ws.web.jeo.user.User;

import java.util.List;

import static org.next.ws.util.Util.assureNotNull;

@JeoController
public class GameController {

    @JeoEvent("card.submit")
    public void gamePlay(@MyTurnOnly User user, Integer id, List<Integer> targetList) {
        user.getPlayer().useCard(id, user.getPlayer().resolveTargetList(targetList));
    }

    @JeoEvent("game.end_turn")
    public void endTurn(User user){
        assureNotNull(user.getPlayer()).getCamp().endTurn();
    }

    @JeoEvent("game.fighter_attack")
    public void attack(@MyTurnOnly User user, Integer by, Integer target){
        Player player = assureNotNull(user.getPlayer());
        Fighter attacker = player.getCamp().getFighterById(by);
        Fighter defender = player.getCamp().getFighterById(target);
        attacker.attack(defender);
        user.getGame().gameStateUpdate();
        user.getGame().broadCast(GameEventType.ATTACK, "공격 : " + attacker.getName() + " -> " + defender.getName());
    }
}
