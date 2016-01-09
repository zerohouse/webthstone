package org.next.ws.core.fighter.property;

import org.next.ws.core.StaticValues;

public class Vital {

    private int minVital;
    private int vital;
    private int maxVital;

    Vital(int vital) {
        this.vital = vital;
        this.maxVital = vital;
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
