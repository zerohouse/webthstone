package org.next.ws.core.fighter.property;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VitalDto {

    private int maxVital;
    private int vital;

    public VitalDto(Vital vital) {
        this.maxVital = vital.maxVital;
        this.vital = vital.vital;
    }
}
