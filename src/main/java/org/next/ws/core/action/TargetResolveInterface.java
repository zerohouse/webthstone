package org.next.ws.core.action;

import org.next.ws.core.fighter.Fighter;
import org.next.ws.core.game.player.Player;

import java.util.List;

public interface TargetResolveInterface {
    List<? extends Fighter> resolve(Player player);
}
