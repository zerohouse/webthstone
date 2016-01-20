package org.next.ws.core.card;

import org.junit.Before;
import org.junit.Test;
import org.next.ws.core.game.Game;
import org.next.ws.core.game.player.ConsoleTestingPlayer;
import org.next.ws.core.game.player.deck.Deck;
import org.next.ws.core.hero.healer.Healer;
import org.next.ws.core.hero.hunter.Hunter;

import java.io.IOException;

public class CardTest {

    private Game game;

    @Before
    public void setup() throws IOException {
        Deck deck = new Deck("[1,2,3,4,5]");
        Deck deck2 = new Deck("[1,2,3,4,5]");
        game = new Game();
        game.setPlayer(new ConsoleTestingPlayer(new Healer("사제", "/resource/icon/coin.img", game), deck), new ConsoleTestingPlayer(new Hunter("냥꾼", "/resource/icon/coin.img", game), deck2));
    }

    @Test
    public void testUsable() throws Exception {
    }

}