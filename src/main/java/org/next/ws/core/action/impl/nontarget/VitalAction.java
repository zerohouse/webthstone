package org.next.ws.core.action.impl.nontarget;

import org.next.ws.core.action.NonTargetAction;
import org.next.ws.core.action.serialize.WsAction;
import org.next.ws.core.action.target.TargetOption;
import org.next.ws.core.action.target.TargetResolve;
import org.next.ws.core.fighter.Fighter;
import org.next.ws.core.game.player.Player;
import org.next.ws.core.type.FixedValue;
import org.next.ws.core.type.RangeAble;
import org.next.ws.core.type.RangeValue;

import java.util.List;
import java.util.Random;


public class VitalAction extends NonTargetAction {

    private final Random random = new Random();

    RangeAble repeat;
    RangeAble amount;
    TargetResolve targetResolve;


    @WsAction(id = "rand_attack", name = "무작위 하수인 피해(치료)", params = {"횟수", "값"}, targetOption = TargetOption.All)
    public VitalAction(String targetResolve, String repeat, String damage) {
        this.targetResolve = TargetResolve.valueOf(targetResolve);
        this.repeat = new FixedValue(Integer.parseInt(repeat));
        this.amount = new FixedValue(Integer.parseInt(damage));
    }

    @WsAction(id = "rand_amount_attack", name = "무작위 하수인 피해(치료)", params = {"최소 횟수", "최대 횟수", "최소 값", "최대 값"}, targetOption = TargetOption.All)
    public VitalAction(String targetResolve, String minRepeat, String maxRepeat, String minDamage, String maxDamage) {
        this.targetResolve = TargetResolve.valueOf(targetResolve);
        this.repeat = new RangeValue(Integer.parseInt(minRepeat), Integer.parseInt(maxRepeat));
        this.amount = new RangeValue(Integer.parseInt(minDamage), Integer.parseInt(maxDamage));
    }

    @Override
    public boolean able(Player player) {
        return targetResolve.resolve(player).size() != 0;
    }

    @Override
    public void act(Player player) {
        for (int i = 0; i < repeat.getValue(); i++)
            randAction(targetResolve.resolve(player));
    }

    private void randAction(List<? extends Fighter> targetList) {
        int size = targetList.size();
        if (size == 0)
            return;
        Fighter target = targetList.get(random.nextInt(size));
        target.addVital(amount.getValue());
    }
}
