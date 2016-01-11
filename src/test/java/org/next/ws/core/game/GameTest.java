package org.next.ws.core.game;

import org.junit.Test;
import org.next.ws.core.event.impl.ConsoleBroadCaster;
import org.next.ws.core.event.impl.ConsoleEventRunner;
import org.next.ws.core.game.camp.Camp;
import org.next.ws.core.game.player.Player;
import org.next.ws.core.game.camp.SinglePlayerCamp;
import org.next.ws.core.game.player.deck.Deck;
import org.next.ws.core.hero.healer.Healer;
import org.next.ws.core.hero.hunter.Hunter;

public class GameTest {

    @Test
    public void testGame() throws Exception {
        Deck deck = new Deck("[1,2,3,4,5]");
        Deck deck2 = new Deck("[1,2,3,4,5]");
        Game game = new Game();
        Camp camp = new SinglePlayerCamp(new Player(new Healer("사제", game), deck));
        Camp camp2 = new SinglePlayerCamp(new Player(new Hunter("냥꾼", game), deck2));
        camp.setBroadCaster(new ConsoleBroadCaster());
        camp.setEventRunner(new ConsoleEventRunner());
        game.setCamp(camp, camp2);
        game.start();
    }


}