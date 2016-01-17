package org.next.ws.core.fighter.property;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttackPowerDto {

    private int originPower;
    private int power;
    private int count;

    public AttackPowerDto(AttackPower attackPower) {
        this.power = attackPower.power;
        this.count = attackPower.count;
        this.originPower = attackPower.originPower;
    }
}
