package org.next.ws.core.action.serialize;

import lombok.Getter;
import lombok.Setter;
import org.next.ws.core.action.target.TargetOption;
import org.next.ws.core.action.target.TargetResolve;

@Getter
@Setter
public class WsActionProperties {
    private String id;
    private String name;
    private String[] params;
    private boolean rangeSelect;
    private TargetResolve[] range;
    private boolean targetNeed;

    public WsActionProperties(WsAction annotation) {
        id = annotation.id();
        name = annotation.name();
        params = annotation.params();
        targetNeed = annotation.targetNeed();
        rangeSelect = !(annotation.target().length == 0 && annotation.targetOption().equals(TargetOption.NONE));
        if (!rangeSelect)
            return;
        if (!annotation.targetOption().equals(TargetOption.NONE)) {
            range = annotation.targetOption().getOptions();
            return;
        }
        range = annotation.target();
    }
}
