package org.next.ws.core.action.impl;

import org.next.ws.core.action.NonTargetAction;
import org.next.ws.core.action.serialize.WsAction;
import org.next.ws.core.fighter.Fighter;
import org.next.ws.core.game.player.Player;
import org.next.ws.core.type.FixedValue;
import org.next.ws.core.type.RangeAble;
import org.next.ws.core.type.RangeValue;

import java.util.List;

public class ManaAddAction extends NonTargetAction {


    @WsAction(id = "manaadd", name = "마나획득", params = "마나량")
    public ManaAddAction(Integer i){
        this.amount = new FixedValue(i);
    }

    @WsAction(id = "manaadd_rand", name = "랜덤 마나 획득", params = {"최소마나", "최대마나"})
    public ManaAddAction(Integer min, Integer max){
        this.amount = new RangeValue(min, max);
    }

    protected RangeAble amount;

    @Override
    public boolean able(Player player) {
        return true;
    }

    @Override
    public void act(Player player) {
        player.getGameHero().getMana().add(amount.getValue());
    }

}
