package org.next.ws.core.fighter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FighterDto {

    private String name;
    private int maxVital;
    private int vital;

    public FighterDto(Fighter fighter) {
        this.name =fighter.getName();
        this.maxVital = fighter.getVital().getMaxVital();
        this.vital = fighter.getVital().getVital();
    }
}
