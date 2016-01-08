package org.next.ws.core.card;

import org.junit.Before;
import org.junit.Test;
import org.next.ws.core.game.Game;
import org.next.ws.core.game.player.Player;
import org.next.ws.core.game.player.SinglePlayerCamp;
import org.next.ws.core.game.player.deck.Deck;
import org.next.ws.core.hero.healer.Healer;
import org.next.ws.core.hero.hunter.Hunter;

import java.io.IOException;

import static org.junit.Assert.*;

public class CardTest {

    private Game game;

    @Before
    public void setup() throws IOException {
        Deck deck = new Deck("[1,2,3,4,5]");
        Deck deck2 = new Deck("[1,2,3,4,5]");
        game = new Game(new SinglePlayerCamp(new Player(new Healer("사제"), deck)), new SinglePlayerCamp(new Player(new Hunter("냥꾼"), deck2)));
    }

    @Test
    public void testUsable() throws Exception {
//        Card card = new Card(DefaultCardTemplate.CARD);
//        card.usable(game.getCampFirst());
    }

}