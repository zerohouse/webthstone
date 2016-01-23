package org.next.ws.core.action.impl.nontarget;

import org.next.ws.core.action.NonTargetAction;
import org.next.ws.core.action.serialize.WsAction;
import org.next.ws.core.game.player.Player;
import org.next.ws.core.type.FixedValue;
import org.next.ws.core.type.RangeAble;
import org.next.ws.core.type.RangeValue;

public class ManaAddAction extends NonTargetAction {

    @WsAction(id = "manaadd", name = "마나 획득", params = "마나")
    public ManaAddAction(String i){
        this.amount = new FixedValue(Integer.parseInt(i));
    }

    @WsAction(id = "manaadd_rand", name = "랜덤 마나 획득", params = {"최소", "최대"})
    public ManaAddAction(String min, String max){
        this.amount = new RangeValue(Integer.parseInt(min), Integer.parseInt(max));
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
