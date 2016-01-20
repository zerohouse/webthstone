package org.next.ws.core.action.serialize;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WsActionProperties {
    private String id;
    private String name;
    private String[] params;

    public WsActionProperties(WsAction annotation) {
        id = annotation.id();
        name = annotation.name();
        params = annotation.params();
    }
}
