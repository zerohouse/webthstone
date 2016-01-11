package org.next.ws.core.game;

import lombok.Getter;
import lombok.ToString;
import org.next.ws.core.game.camp.Camp;
import org.next.ws.core.event.standard.EventResult;
import org.next.ws.core.event.standard.EventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

@Getter
@ToString
public class Game {

    private static final Logger logger = LoggerFactory.getLogger(Game.class);

    private Random random;

    public Game() {
        this.phase = 0;
        random = new Random();
    }

    public void setCamp(Camp one, Camp another) {
        one.setEnemy(another);
        one.setGame(this);
        another.setEnemy(one);
        another.setGame(this);

        boolean first = random.nextBoolean();
        if (first) {
            this.campFirst = one;
            this.campSecond = another;
        } else {
            this.campFirst = another;
            this.campSecond = one;
        }
    }

    private int phase;

    private Camp campFirst;
    private Camp campSecond;

    public void start() {
        logger.debug("game start");
        campFirst.ready(true);
        campSecond.ready(false);
        phaseStart();
    }

    private void phaseStart() {
        logger.debug("phase start");
        phase++;
        campFirst.startTurn();
    }

    public void turnOver() {
        logger.debug("turn over");
        boolean isFirst = campFirst.isTurn();
        if (isFirst) {
            campFirst.endTurn();
            campSecond.startTurn();
            return;
        }
        campSecond.endTurn();
        phaseStart();
    }

    public void broadCast(EventType type, EventResult result){
        campFirst.broadCast(type, result);
        campSecond.broadCast(type, result);
    }

    public void end() {


    }
}
