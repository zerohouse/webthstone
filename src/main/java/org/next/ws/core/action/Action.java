package org.next.ws.core.action;

import org.next.ws.core.card.exception.CardUnUsableException;
import org.next.ws.core.fighter.Fighter;
import org.next.ws.core.game.player.Player;

import java.util.List;


/*
*
* 키를 가져갈때
*
* Integer id | parameters로 작성
*
* ex) 1|3,2,1
*
* 각 구현체는 인자가 String 하나인 구현체가 있어야 한다.
*
* */

public interface Action {
    boolean able(Player player, List<Fighter> targetList) throws CardUnUsableException;

    void act(Player player, List<Fighter> targetList);


    static Action getAction(String key) {
        return ActionBuilder.getAction(key);
    }

    static NonTargetAction getNontargetAction(String deathAction) {
        return null;
    }

    static List<NonTargetAction> getNonTargetActionList(String deathAction) {
        return null;
    }
}
