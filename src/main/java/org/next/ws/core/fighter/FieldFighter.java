package org.next.ws.core.fighter;

import org.next.ws.core.StaticValues;
import org.next.ws.core.action.NonTargetAction;
import org.next.ws.core.fighter.property.AttackPower;
import org.next.ws.core.fighter.property.Vital;
import org.next.ws.core.game.event.Event;
import org.next.ws.core.game.event.EventType;
import org.next.ws.core.game.player.Player;
import org.next.ws.core.scanner.ComponentScanner;

import java.util.Map;

public class FieldFighter extends Fighter {

    private Map<EventType, NonTargetAction> eventListMap;

    public FieldFighter(FighterTemplate fighterTemplate, Player player) {
        super(new AttackPower(fighterTemplate.getAttack()), new Vital(fighterTemplate.getVital()), fighterTemplate.getImg(), fighterTemplate.getName());
        setId(player.getGame().getNextId());
        setPlayer(player);
        getAttackPower().setCount(StaticValues.DEFAULT_ATTACK_COUNT_WHEN_PLAYED);
        fighterTemplate.getActionList().forEach(actionTemplate -> {
            eventListMap.put(actionTemplate.getTiming(), ComponentScanner.getNonTargetAction(actionTemplate));
        });
    }

    @Override
    public void die() {
        player.removeFighter(this);
    }

    public void broadCast(Event event) {

    }
}
