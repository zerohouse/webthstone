package org.next.ws.core.game;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameStateDto {
    private int phase;

    public GameStateDto(Game game) {
        this.phase = game.getPhase();
    }
}
