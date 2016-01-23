package org.next.ws.core.action.impl.nontarget;

import org.next.ws.core.action.NonTargetAction;
import org.next.ws.core.action.serialize.WsAction;
import org.next.ws.core.action.target.TargetResolve;
import org.next.ws.core.fighter.Fighter;
import org.next.ws.core.game.player.Player;
import org.next.ws.core.type.FixedValue;
import org.next.ws.core.type.RangeAble;
import org.next.ws.core.type.RangeValue;

import java.util.List;
import java.util.Random;

public class RandomTargetAssassinateAction extends NonTargetAction {

    private final Random random = new Random();

    RangeAble repeat;
    TargetResolve targetResolve;

    @WsAction(id = "rand_target_assinate", name = "무작위 하수인 제거", params = {"타겟 수"},
            target = {TargetResolve.ALL_FIELD_FIGHTERS, TargetResolve.MY_FIELD_FIGHTERS, TargetResolve.ENEMY_FIELD_FIGHTER})
    public RandomTargetAssassinateAction(String targetResolve, String amount) {
        this.targetResolve = TargetResolve.valueOf(targetResolve);
        repeat = new FixedValue(Integer.parseInt(amount));
    }

    @WsAction(id = "rand_many_target_assinate", name = "무작위 하수인 제거(랜덤 횟수)", params = {"최소", "최대"},
            target = {TargetResolve.ALL_FIELD_FIGHTERS, TargetResolve.MY_FIELD_FIGHTERS, TargetResolve.ENEMY_FIELD_FIGHTER})
    public RandomTargetAssassinateAction(String targetResolve, String minAmount, String maxAmount) {
        this.targetResolve = TargetResolve.valueOf(targetResolve);
        repeat = new RangeValue(Integer.parseInt(minAmount), Integer.parseInt(maxAmount));
    }

    @Override
    public boolean able(Player player) {
        return true;
    }

    @Override
    public void act(Player player) {
        randAction(targetResolve.resolve(player));
    }

    private void randAction(List<? extends Fighter> targetList) {
        int size = targetList.size();
        if (size == 0)
            return;
        Fighter target = targetList.get(random.nextInt(size));
        target.die();
    }
}
