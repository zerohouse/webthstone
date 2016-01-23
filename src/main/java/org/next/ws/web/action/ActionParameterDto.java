package org.next.ws.web.action;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ActionParameterDto {
    private String effect;
    private List<Object> params;
    private String range;
    private String timing;
}
