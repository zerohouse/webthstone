package org.next.ws.core.game;

import org.junit.Test;
import org.next.ws.core.game.player.ConsoleTestingPlayer;
import org.next.ws.core.game.player.Player;
import org.next.ws.core.game.player.deck.Deck;
import org.next.ws.core.hero.healer.Healer;
import org.next.ws.core.hero.hunter.Hunter;

public class GameTest {

    @Test
    public void testGame() throws Exception {
        Deck deck = new Deck("[1,2,3,4,5]");
        Deck deck2 = new Deck("[1,2,3,4,5]");
        Game game = new Game();
        Player p1 = new ConsoleTestingPlayer(new Healer("사제", "/resource/icon/coin.img", game), deck);
        Player p2 = new ConsoleTestingPlayer(new Hunter("냥꾼", "/resource/icon/coin.img", game), deck2);
        game.setPlayer(p1, p2);
        game.start();
    }


}