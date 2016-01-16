package org.next.ws.web.game;

import org.next.ws.core.game.Game;
import org.next.ws.core.game.camp.SinglePlayerCamp;
import org.next.ws.core.game.player.deck.Deck;
import org.next.ws.core.hero.healer.Healer;
import org.next.ws.web.matching.SocketUserPlayer;
import org.next.ws.web.user.User;

import java.io.IOException;

public class Match {

    private Game game;

    public Match(User user, User user2) {
        this.game = new Game();
        try {
            game.setCamp(new SinglePlayerCamp(new SocketUserPlayer(user, new Healer("사제", game), new Deck("[1,2,3,4,5,1,2,3,4,5,1,2,3]"))),
                    new SinglePlayerCamp(new SocketUserPlayer(user2, new Healer("사제", game), new Deck("[1,2,3,4,5,1,2,3,4,5,1,2,3]"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        game.start();
    }

}
