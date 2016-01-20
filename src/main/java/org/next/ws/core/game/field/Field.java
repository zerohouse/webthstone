package org.next.ws.core.game.field;

import lombok.Getter;
import lombok.ToString;
import org.next.ws.core.StaticValues;
import org.next.ws.core.fighter.FieldFighter;
import org.next.ws.core.fighter.Fighter;
import org.next.ws.core.fighter.FighterDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ToString
@Getter
public class Field {

    private int fieldMax;
    List<FieldFighter> fighters;

    public Field() {
        fieldMax = StaticValues.DEFAULT_FIELD_MAX_SIZE;
        fighters = new ArrayList<>();
    }

    public boolean addAble() {
        return this.fighters.size() + 1 < fieldMax;
    }

    public void addFighter(FieldFighter fighter) {
        this.fighters.add(fighter);
    }

    public List<FighterDto> getFighterDtoList() {
        return fighters.stream().map(FighterDto::new).collect(Collectors.toList());
    }

    public void startTurn() {
        fighters.forEach(Fighter::startTurn);
    }

    public void endTurn() {
        fighters.forEach(Fighter::endTurn);
    }

    public void removeFighter(FieldFighter fieldFighter) {
        fighters.remove(fieldFighter);
    }
}
