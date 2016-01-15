package org.next.ws.web.game;

import io.vertx.ext.web.handler.sockjs.SockJSSocket;
import org.next.ws.core.game.Game;
import org.next.ws.core.game.camp.SinglePlayerCamp;
import org.next.ws.core.game.player.deck.Deck;
import org.next.ws.core.hero.healer.Healer;
import org.next.ws.web.matching.SocketPlayer;

import java.io.IOException;

public class Match {

    private Game game;

    public Match(SockJSSocket sockJSSocket, SockJSSocket sockJSSocket2) {
        this.game = new Game();
        try {
            game.setCamp(new SinglePlayerCamp(new SocketPlayer(sockJSSocket, new Healer("사제", game), new Deck("[1,2,3,4,5,1,2,3,4,5,1,2,3]"))),
                    new SinglePlayerCamp(new SocketPlayer(sockJSSocket2, new Healer("사제", game), new Deck("[1,2,3,4,5,1,2,3,4,5,1,2,3]"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        game.start();
    }

}
