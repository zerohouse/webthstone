package org.next.ws.core.fighter.property;

public class AttackPower {

    private int originPower;
    private int power;
    private int count;

    public static AttackPower getDefaultAttackPower() {
        return new AttackPower();
    }
}
