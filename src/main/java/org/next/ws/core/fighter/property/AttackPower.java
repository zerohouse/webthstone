package org.next.ws.core.fighter.property;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AttackPower {

    int originPower;
    int power;
    int originCount;
    int count;

    public AttackPower(Integer attack) {
        this.originPower = this.power = attack;
        this.count = 0;
        this.originCount = 1;
    }

    public static AttackPower getDefaultAttackPower() {
        return new AttackPower(0);
    }

    public void startTurn() {
        this.count = originCount;
    }

    public void attack() {
        this.count--;
    }

    public boolean isAttackAble() {
        return count > 0;
    }
}
