package org.next.ws.gamelauncher;

import org.next.ws.core.game.Game;
import org.next.ws.gamelauncher.broadcaster.BroadCaster;

public class GameLauncher {
    private Game game;
    private BroadCaster broadCaster;

    public GameLauncher(Game game, BroadCaster broadCaster) {
        this.game = game;
        this.broadCaster = broadCaster;
    }

    public void start() {
        game.start();
        broadCaster.broadCast(game);

    }

    public void waitEvent() {

    }
}
