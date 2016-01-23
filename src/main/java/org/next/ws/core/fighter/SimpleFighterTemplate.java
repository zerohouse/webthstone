package org.next.ws.core.fighter;

import lombok.Getter;
import org.next.ws.core.action.serialize.ActionTemplate;

import java.util.List;

@Getter
public class SimpleFighterTemplate implements FighterTemplate {


    private String name;
    private String img;
    private Integer attack;
    private Integer vital;

    public SimpleFighterTemplate(String name, String img, Integer attack, Integer vital) {
        this.name = name;
        this.img = img;
        this.attack = attack;
        this.vital = vital;
    }

    @Override
    public List<ActionTemplate> getActionList() {
        return null;
    }
}
