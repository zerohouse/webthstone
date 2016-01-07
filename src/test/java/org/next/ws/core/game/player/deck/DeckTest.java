package org.next.ws.core.game.player.deck;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DeckTest {

    private Deck deck;

    @Before
    public void setup() throws Exception {
        deck = new Deck("[1,2,3,4,5]");
    }

    @Test
    public void testCount() throws Exception {
        assertEquals(deck.countCard(), 5);
    }

    @Test
    public void testShuffle() throws Exception {
        deck.shuffle();
        System.out.println(deck);
        deck.shuffle();
        System.out.println(deck);
    }

    @Test
    public void drawCard() throws Exception {
        deck.shuffle();
        System.out.println(deck);
        System.out.println(deck.drawCard());
        System.out.println(deck);
    }


}