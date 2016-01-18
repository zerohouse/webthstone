package org.next.ws.core.game.camp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.next.ws.core.StaticValues;
import org.next.ws.core.action.Action;
import org.next.ws.core.event.standard.GameEventType;
import org.next.ws.core.fighter.FieldFighter;
import org.next.ws.core.fighter.Fighter;
import org.next.ws.core.game.Game;
import org.next.ws.core.game.player.Player;

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
    protected Game game;

    public abstract void ready(boolean first);

    public abstract Player getPlayingPlayer();

    public abstract List<Fighter> getAllFighters();

    public abstract List<Fighter> getFieldFighters();

    public abstract String getName();

    public abstract void startTurn();

    public abstract void endTurn();


    public abstract void broadCast(GameEventType gameEvent, Object result);

    protected abstract void broadCast(GameEventType gameEvent);

    public abstract void gameStateUpdate();

    public Camp() {
        startTurnEffects = new ArrayList<>();
        endTurnEffects = new ArrayList<>();
    }


    public abstract void generateCardIdInGame(Game game);

    public Fighter getFighterById(Integer id) {
        if (id.equals(StaticValues.MY_GAME_HERO_ID))
            return getPlayingPlayer().getGameHero();
        if (id.equals(StaticValues.ENEMY_GAME_HERO_ID))
            return enemy.getPlayingPlayer().getGameHero();
        if (getFieldFighters().stream().filter(fighter -> id.equals(fighter.getId())).findAny().isPresent())
            return getFieldFighters().stream().filter(fighter -> id.equals(fighter.getId())).findAny().get();
        if (enemy.getFieldFighters().stream().filter(fighter -> id.equals(fighter.getId())).findAny().isPresent())
            return enemy.getFieldFighters().stream().filter(fighter -> id.equals(fighter.getId())).findAny().get();
        return null;
    }


    public abstract void removeFighter(FieldFighter fieldFighter);

    public abstract void addFighter(Fighter fighter);

    public abstract void lose();

    public abstract void win();
}
