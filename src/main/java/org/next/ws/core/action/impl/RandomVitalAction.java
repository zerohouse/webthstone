package org.next.ws.core.action.impl;

import org.next.ws.core.action.NonTargetAction;
import org.next.ws.core.action.TargetResolve;
import org.next.ws.core.action.serialize.WsAction;
import org.next.ws.core.card.exception.CardUnUsableException;
import org.next.ws.core.fighter.Fighter;
import org.next.ws.core.game.player.Player;
import org.next.ws.core.type.FixedValue;
import org.next.ws.core.type.RangeAble;

import java.util.List;
import java.util.Random;


public class RandomVitalAction extends NonTargetAction {

    private final Random random = new Random();

    RangeAble repeat;
    RangeAble amount;
    TargetResolve targetResolve;


    @WsAction(id = "enemy_field_rand_attack", name = "랜덤 피해", params = {"반복횟수","피해량"})
    public RandomVitalAction(Integer repeat, Integer damage) {
        this.repeat = new FixedValue(repeat);
        this.amount = new FixedValue(damage);
        targetResolve = TargetResolve.ENEMY_FIELD_FIGHTER;
    }

    @Override
    public boolean able(Player player) {
        return targetResolve.resolve(player).size() != 0;
    }

    @Override
    public void act(Player player) {
        for (int i = 0; i < repeat.getValue(); i++)
            randAttack(targetResolve.resolve(player));
    }

    private void randAttack(List<? extends Fighter> targetList) {
        int size = targetList.size();
        if (size == 0)
            return;
        Fighter target = targetList.get(random.nextInt(size));
        target.addVital(amount.getValue());
    }
}
