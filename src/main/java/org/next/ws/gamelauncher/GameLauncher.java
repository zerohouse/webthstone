package org.next.ws.gamelauncher;

import org.next.ws.core.game.Game;

public class GameLauncher {
    private Game game;

    public GameLauncher(Game game) {
        this.game = game;
    }

    public void start() {
        game.start();
    }

}
