package org.next.ws.core.game.field;

import lombok.Getter;
import lombok.ToString;
import org.next.ws.core.StaticValues;
import org.next.ws.core.fighter.Fighter;
import org.next.ws.core.fighter.FighterDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ToString
@Getter
public class Field {

    private int fieldMax;
    List<Fighter> fighters;

    public Field() {
        fieldMax = StaticValues.DEFAULT_FIELD_MAX_SIZE;
        fighters = new ArrayList<>();
    }

    public boolean addAble(List<Fighter> fighters) {
        return fighters.size() + this.fighters.size() > fieldMax;
    }

    public void addFighters(List<Fighter> fighters) {
        this.fighters.addAll(fighters);
    }

    public List<FighterDto> getFighterDtoList() {
        return fighters.stream().map(FighterDto::new).collect(Collectors.toList());
    }
}
