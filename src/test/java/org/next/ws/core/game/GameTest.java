package org.next.ws.core.game;

import org.junit.Test;
import org.next.ws.core.game.player.Player;
import org.next.ws.core.game.player.SinglePlayerCamp;
import org.next.ws.core.game.player.deck.Deck;
import org.next.ws.core.hero.healer.Healer;
import org.next.ws.core.hero.hunter.Hunter;

import static org.junit.Assert.assertEquals;

public class GameTest {

    @Test
    public void testGame() throws Exception {
        Deck deck = new Deck("[1,2,3,4,5]");
        Deck deck2 = new Deck("[1,2,3,4,5]");
        Game game = new Game(new SinglePlayerCamp(new Player(new Healer("사제"), deck)), new SinglePlayerCamp(new Player(new Hunter("냥꾼"), deck2)));
        game.start();
    }


}