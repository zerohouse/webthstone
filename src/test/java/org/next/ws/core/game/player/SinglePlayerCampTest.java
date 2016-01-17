package org.next.ws.core.game.player;

import org.junit.Before;
import org.junit.Test;
import org.next.ws.core.game.camp.SinglePlayerCamp;
import org.next.ws.core.game.player.deck.Deck;
import org.next.ws.core.hero.healer.Healer;

import static org.junit.Assert.assertEquals;

public class SinglePlayerCampTest {

    private SinglePlayerCamp singlePlayerCamp;

    @Before
    public void setUp() throws Exception {
        singlePlayerCamp = new SinglePlayerCamp(new ConsoleTestingPlayer(new Healer("사제", "/resource/icon/coin.img", null), new Deck("[1,2,3,4,5,1,2,3,4,5,1,2,3]")));
    }

    @Test
    public void testReady() throws Exception {
        singlePlayerCamp.ready(true);
        assertEquals(3, singlePlayerCamp.getPlayingPlayer().getHand().countCard());
        assertEquals(10, singlePlayerCamp.getPlayingPlayer().getDeck().countCard(), 8);
        assertEquals(13, singlePlayerCamp.getPlayingPlayer().countCard());
    }

    @Test
    public void testReady2() throws Exception {
        singlePlayerCamp.ready(false);
        assertEquals(5, singlePlayerCamp.getPlayingPlayer().getHand().countCard());
        assertEquals(9, singlePlayerCamp.getPlayingPlayer().getDeck().countCard(), 8);
        assertEquals(14, singlePlayerCamp.getPlayingPlayer().countCard());
    }


    @Test
    public void testTurnStartAction() throws Exception {

    }

    @Test
    public void testTurnEndAction() throws Exception {

    }
}