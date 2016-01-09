package org.next.ws.core.type;

import lombok.Getter;

@Getter
public class FixedValue implements RangeAble {
    private int value;

    public FixedValue(int value) {
        this.value = value;
    }
}
