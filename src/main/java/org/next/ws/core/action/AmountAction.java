package org.next.ws.core.action;

import org.next.ws.core.type.FixedValue;
import org.next.ws.core.type.RangeAble;

public abstract class AmountAction implements Action {

    protected final RangeAble amount;

    protected AmountAction(String amount) {
        if ("".equals(amount)) {
            this.amount = new FixedValue(1);
            return;
        }
        this.amount = RangeAble.get(amount);
    }
}
