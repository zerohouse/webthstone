package org.next.ws.core.fighter;

import lombok.Getter;
import lombok.Setter;
import org.next.ws.core.fighter.property.AttackPowerDto;
import org.next.ws.core.fighter.property.VitalDto;

@Getter
@Setter
public class FighterDto {

    private String name;
    private String img;
    private Integer id;
    private VitalDto vital;
    private AttackPowerDto attack;

    public FighterDto(Fighter fighter) {
        this.name = fighter.getName();
        this.img= fighter.getImg();
        this.id = fighter.getId();
        this.vital = new VitalDto(fighter.getVital());
        this.attack = new AttackPowerDto(fighter.getAttackPower());
    }
}
