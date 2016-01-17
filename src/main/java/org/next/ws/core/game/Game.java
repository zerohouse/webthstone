package org.next.ws.core.game;

import lombok.Getter;
import lombok.ToString;
import org.next.ws.core.event.standard.GameEventType;
import org.next.ws.core.game.camp.Camp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

@Getter
@ToString
public class Game {

    private static final Logger logger = LoggerFactory.getLogger(Game.class);

    private Random random;

    public Game() {
        random = new Random();
    }

    public void setCamp(Camp one, Camp another) {
        one.setEnemy(another);
        one.setGame(this);
        one.generateCardIdInGame(this);
        another.setEnemy(one);
        another.setGame(this);
        another.generateCardIdInGame(this);

        boolean first = random.nextBoolean();
        if (first) {
            this.campFirst = one;
            this.campSecond = another;
        } else {
            this.campFirst = another;
            this.campSecond = one;
        }
    }

    private Camp campFirst;
    private Camp campSecond;

    public void start() {
        logger.debug("game start");
        campFirst.ready(true);
        campSecond.ready(false);
        gameStateUpdate();
        broadCast(GameEventType.START);
        campFirst.startTurn();
    }

    private void broadCast(GameEventType start) {
        broadCast(start, null);
    }

    public void broadCast(GameEventType type, Object result) {
        campFirst.broadCast(type, result);
        campSecond.broadCast(type, result);
    }

    public void gameStateUpdate() {
        campFirst.gameStateUpdate();
        campSecond.gameStateUpdate();
    }

    public void end() {

    }


    /*
    * 0 = 내 히어로
    * 1 = 적 히어로
    * */

    private int id = 2;

    public int getNextId() {
        return id++;
    }
}
