package org.next.ws.core.action;

import org.next.ws.core.type.FixedValue;
import org.next.ws.core.type.RangeAble;

public abstract class RepeatAction implements Action {

    protected final RangeAble repeat;

    protected RepeatAction(String amount) {
        if ("".equals(amount)) {
            this.repeat = new FixedValue(1);
            return;
        }
        this.repeat = RangeAble.get(amount);
    }
}
