package org.next.ws.core.game;

import lombok.Getter;
import lombok.ToString;
import org.next.ws.core.action.NonTargetAction;
import org.next.ws.core.game.player.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Getter
@ToString
public class Game {

    private static final Logger logger = LoggerFactory.getLogger(Game.class);

    private Random random;

    public Game() {
        random = new Random();
    }

    public final Map<GameEvent, List<NonTargetAction>> events = new HashMap<>();

    public void eventOccur(GameEvent type, Player trigger) {
        events.get(type).forEach(nonTargetAction -> {
            if (nonTargetAction.able(trigger))
                nonTargetAction.act(trigger);
        });
    }

    public void setPlayer(Player one, Player another) {
        one.setEnemy(another);
        one.setGame(this);
        one.generateCardIdInGame(this);
        another.setEnemy(one);
        another.setGame(this);
        another.generateCardIdInGame(this);

        boolean first = random.nextBoolean();
        if (first) {
            this.first = one;
            this.secoond = another;
        } else {
            this.first = another;
            this.secoond = one;
        }
    }

    private Player first;
    private Player secoond;

    public void start() {
        logger.debug("game start");
        first.ready(true);
        secoond.ready(false);
        gameStateUpdate();
        broadCast(GameEvent.START);
        first.startTurn();
    }

    private void broadCast(GameEvent start) {
        broadCast(start, null);
    }

    public void broadCast(GameEvent type, Object result) {
        first.broadCast(type, result);
        secoond.broadCast(type, result);
    }

    public void gameStateUpdate() {
        first.gameStateUpdate();
        secoond.gameStateUpdate();
    }

    public void end() {

    }


    /*
    * 0 = 내 히어로
    * 1 = 적 히어로
    * ~
    * 99번까지 예약.
    *
    *
    * */

    private int id = 100;

    public int getNextId() {
        return id++;
    }
}
