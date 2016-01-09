package org.next.ws.core.type;

import java.util.Random;

public class RangeValue implements RangeAble {

    private Random random;
    private int min;
    private int max;

    public RangeValue(int min, int max) {
        random = new Random();
        this.min = min;
        this.max = max;
    }

    @Override
    public int getValue() {
        return min + random.nextInt(max - min);
    }
}
