package org.next.ws.web.fighter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.next.ws.web.action.ActionEntityDto;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class FighterEntityDto {

    private List<ActionEntityDto> actions;

    private Long id;

    private String name;

    private String img;

    private Integer vital;

    private Integer attack;

    public FighterEntityDto(FighterEntity fighterTemplate) {
        this.id = fighterTemplate.getId();
        this.name = fighterTemplate.getName();
        this.img = fighterTemplate.getImg();
        this.vital = fighterTemplate.getVital();
        this.attack = fighterTemplate.getAttack();
        this.actions = fighterTemplate.getFighterHasActions().stream().map(fighterHasAction -> new ActionEntityDto(fighterHasAction.getActionEntity())).collect(Collectors.toList());
    }
}
