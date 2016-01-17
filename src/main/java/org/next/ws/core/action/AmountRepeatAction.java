package org.next.ws.core.action;


import org.next.ws.core.type.FixedValue;
import org.next.ws.core.type.RangeAble;

public abstract class AmountRepeatAction implements Action {

    protected final RangeAble amount;
    protected final RangeAble repeat;


    protected AmountRepeatAction(String actionString) {
        if ("".equals(actionString)) {
            this.amount = new FixedValue(1);
            this.repeat = new FixedValue(1);
            return;
        }
        if (!actionString.contains(",")) {
            this.repeat = new FixedValue(1);
            this.amount = RangeAble.get(actionString);
            return;
        }
        String[] params = actionString.split(",");
        this.amount = RangeAble.get(params[0]);
        this.repeat = RangeAble.get(params[1]);
    }

}
