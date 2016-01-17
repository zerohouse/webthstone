package org.next.ws.gamelauncher;

import org.junit.Before;
import org.junit.Test;
import org.next.ws.core.game.Game;
import org.next.ws.core.game.camp.SinglePlayerCamp;
import org.next.ws.core.game.player.ConsoleTestingPlayer;
import org.next.ws.core.game.player.deck.Deck;
import org.next.ws.core.hero.healer.Healer;
import org.next.ws.core.hero.hunter.Hunter;

public class GameLauncherTest {

    private GameLauncher gameLauncher;

    @Before
    public void setUp() throws Exception {
        Deck deck = new Deck("[1,2,3,4,5]");
        Deck deck2 = new Deck("[1,2,3,4,5]");
        Game game = new Game();
        game.setCamp(new SinglePlayerCamp(new ConsoleTestingPlayer(new Healer("사제", "/resource/icon/coin.img", game), deck)), new SinglePlayerCamp(new ConsoleTestingPlayer(new Hunter("냥꾼", "/resource/icon/coin.img", game), deck2)));

    }

    @Test
    public void test() {
        gameLauncher.start();
    }

}