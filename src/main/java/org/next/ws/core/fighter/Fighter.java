package org.next.ws.core.fighter;

import lombok.Getter;
import lombok.Setter;
import org.next.ws.core.fighter.property.AttackPower;
import org.next.ws.core.fighter.property.MagicPower;
import org.next.ws.core.fighter.property.Species;
import org.next.ws.core.fighter.property.Vital;
import org.next.ws.core.game.event.EventType;
import org.next.ws.core.game.player.Player;

@Setter
@Getter
public abstract class Fighter {

    protected final AttackPower attackPower;
    protected final Vital vital;
    protected MagicPower magicPower;
    protected Species species;
    protected FighterState fighterState;
    protected String name;
    protected String img;
    protected int id;
    protected Player player;

    protected Fighter(AttackPower attackPower, Vital vital, String img, String name) {
        this.name = name;
        this.img = img;
        this.attackPower = attackPower;
        this.vital = vital;
    }


    public void addVital(int amount) {
        vital.add(amount);
        deadCheck();
    }

    private void deadCheck() {
        if (!vital.isAlive())
            die();
    }

    public abstract void die();

    public void attack(Fighter defender) {
        if (!attackPower.isAttackAble()) {
            player.broadCast(EventType.WARN, "공격 가능한 상태가 아닙니다.");
            return;
        }
        attackPower.attack();
        addVital(-defender.attackPower.getPower());
        defender.addVital(-attackPower.getPower());
    }

    public void startTurn() {
        this.attackPower.startTurn();
    }

    public void endTurn() {
    }

}
