package org.next.ws.core.game;

import lombok.Getter;
import lombok.ToString;
import org.next.ws.core.game.camp.Camp;

@Getter
@ToString
public class Game {

    public Game(Camp campFirst, Camp campSecond) {
        this.turn = 0;
        this.campFirst = campFirst;
        this.campSecond = campSecond;
    }

    private Integer turn;

    private Camp campFirst;
    private Camp campSecond;

}
