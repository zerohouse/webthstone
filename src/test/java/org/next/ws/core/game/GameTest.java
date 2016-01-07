package org.next.ws.core.game;

import org.junit.Test;
import org.next.ws.core.card.Card;
import org.next.ws.core.game.camp.Camp;
import org.next.ws.core.game.player.Player;
import org.next.ws.core.game.player.deck.Deck;
import org.next.ws.core.hero.healer.Healer;
import org.next.ws.core.hero.hunter.Hunter;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GameTest {

    @Test
    public void testGame() throws Exception {
        Deck deck = new Deck("[1,2,3,4,5]");
        Deck deck2 = new Deck("[1,2,3,4,5]");
        Game game = new Game(new Camp(new Player(new Healer("사제"), deck)), new Camp(new Player(new Hunter("냥꾼"), deck2)));
        List<Card> p1Cards = game.getCampFirst().getPlayers().get(0).getDeck().getCards();
        System.out.println(p1Cards);
        assertEquals(p1Cards.size(), 5);
    }


}