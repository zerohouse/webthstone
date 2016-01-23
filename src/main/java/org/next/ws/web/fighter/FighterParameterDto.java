package org.next.ws.web.fighter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.next.ws.web.action.ActionParameterDto;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class FighterParameterDto {
    List<ActionParameterDto> effects;
    private int attack;
    private int vital;
}
