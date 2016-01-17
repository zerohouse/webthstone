package org.next.ws.core.fighter.property;

import lombok.Getter;
import org.next.ws.core.StaticValues;

@Getter
public class Vital {

    int minVital;
    int vital;
    int maxVital;

    public Vital(int vital) {
        this.vital = this.maxVital = vital;
        this.minVital = StaticValues.DEFAULT_MIN_VITAL;
    }

    public void add(int amount) {
        vital += amount;
        if (maxVital < vital)
            this.vital = maxVital;
    }

    public boolean isAlive() {
        return minVital < vital;
    }

    public static Vital getDefaultHeroVital() {
        return new Vital(30);
    }

}
