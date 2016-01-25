package org.next.ws.web.card;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.next.ws.web.action.ActionParameterDto;
import org.next.ws.web.fighter.FighterParameterDto;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CardParameterDto {
    private int cost;
    private String detail;
    private String name;
    private String img;
    private boolean fighterCard;
    private ActionParameterDto effect;
    private FighterParameterDto fighter;
}
