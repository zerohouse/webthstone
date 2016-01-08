package org.next.ws.core.game.camp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.next.ws.core.fighter.Fighter;
import org.next.ws.core.game.field.Field;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Getter
@Setter
@ToString(exclude = "enemy")
public abstract class Camp {

    protected List<Consumer<Camp>> startTurnEffects;
    protected List<Consumer<Camp>> endTurnEffects;
    protected Camp enemy;
    protected boolean turn;
    protected final Field field;


    public abstract void ready(boolean first);

    protected abstract void turnStartAction();

    protected abstract void turnEndAction();

    public abstract String getName();

    public boolean addAble(List<Fighter> fighters) {
        return field.addAble(fighters);
    }

    public void addFighters(List<Fighter> fighters) {
        field.addFighters(fighters);
    }

    public Camp() {
        field = new Field();
        startTurnEffects = new ArrayList<>();
        endTurnEffects = new ArrayList<>();
    }

    public void startTurn() {

        startTurnEffects.forEach(action -> {
            action.accept(this);
        });
        this.turnStartAction();
        this.turn = true;
    }

    public void endTurn() {
        endTurnEffects.forEach(action -> {
            action.accept(this);
        });
        this.turnEndAction();
        this.turn = false;
    }


}
