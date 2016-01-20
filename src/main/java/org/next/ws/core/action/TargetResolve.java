package org.next.ws.core.action;

import org.next.ws.core.fighter.Fighter;
import org.next.ws.core.game.player.Player;

import java.util.List;

public enum TargetResolve {
    ENEMY_FIELD_FIGHTER((TargetResolveInterface) player -> player.getEnemy().getFieldFighters()),
    ENEMY_FIGHTER((TargetResolveInterface) player -> player.getEnemy().getFighters()),
    MY_FIGHTERS((TargetResolveInterface) player -> player.getFighters()),
    MY_FIELD_FIGHTERS((TargetResolveInterface) player -> player.getFieldFighters()),
    ALL_FIELD_FIGHTERS((TargetResolveInterface) player -> player.getAllFieldFighters()),
    ALL_FIGHTERS((TargetResolveInterface) player -> player.getAllFighters());

    private TargetResolveInterface targetResolveInterface;

    TargetResolve(TargetResolveInterface targetResolveInterface) {
        this.targetResolveInterface = targetResolveInterface;
    }

    public List<? extends Fighter> resolve(Player player) {
        return targetResolveInterface.resolve(player);
    }
}
