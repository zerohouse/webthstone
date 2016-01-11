package org.next.ws.core.game.camp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.next.ws.core.action.Action;
import org.next.ws.core.event.EventRunner;
import org.next.ws.core.fighter.Fighter;
import org.next.ws.core.event.standard.EventResult;
import org.next.ws.core.event.standard.EventType;
import org.next.ws.core.game.Game;
import org.next.ws.core.game.field.Field;
import org.next.ws.core.game.player.Player;
import org.next.ws.core.event.BroadCaster;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString(exclude = "enemy")
public abstract class Camp {

    protected List<Action> startTurnEffects;
    protected List<Action> endTurnEffects;
    protected Camp enemy;
    protected boolean turn;
    protected final Field field;
    protected BroadCaster broadCaster;
    protected EventRunner eventRunner;
    protected Game game;

    public abstract void setEventRunner(EventRunner eventRunner);

    public abstract void setBroadCaster(BroadCaster broadCaster);

    public abstract void ready(boolean first);

    protected abstract void turnStartAction();

    protected abstract void turnEndAction();

    public abstract Player getPlayingPlayer();

    public abstract List<Fighter> getAllFighters();

    public abstract List<Fighter> getFieldFighters();

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
        startTurnEffects.forEach(Action::act);
        this.turnStartAction();
        this.turn = true;
    }

    public void endTurn() {
        endTurnEffects.forEach(Action::act);
        this.turnEndAction();
        this.turn = false;
    }

    public void broadCast(EventType type, EventResult result) {
        broadCaster.broadCast(type, result);
    }

}
