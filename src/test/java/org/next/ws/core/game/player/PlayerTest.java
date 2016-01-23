package org.next.ws.core.game.player;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayerTest {

    private Player player;

    @Before
    public void setUp() throws Exception {
    }


    @Test
    public void testSize() throws Exception {
        assertEquals(13, player.getDeck().countCard());
    }

    @Test
    public void testDrawCard1() throws Exception {
        player.drawCard(5);
        assertEquals(5, player.getHand().countCard());
        assertEquals(8, player.getDeck().countCard(), 8);
    }

    @Test
    public void testDrawCard2() throws Exception {
        player.drawCard(15);
        assertEquals(10, player.getHand().countCard());
        assertEquals(3, player.getHand().getBurnedSize());
        assertEquals(0, player.getDeck().countCard());
        assertEquals(2, player.getNullDeckCount());
    }
}