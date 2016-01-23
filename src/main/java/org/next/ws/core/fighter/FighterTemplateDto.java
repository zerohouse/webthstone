package org.next.ws.core.fighter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FighterTemplateDto {

    private String name;
    private String img;
    private Integer vital;
    private Integer attack;

    public FighterTemplateDto(FighterTemplate fighter) {
        this.name = fighter.getName();
        this.img= fighter.getImg();
        this.vital = fighter.getVital();
        this.attack = fighter.getAttack();
    }
}
