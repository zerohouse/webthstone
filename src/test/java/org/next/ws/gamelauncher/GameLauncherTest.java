package org.next.ws.gamelauncher;

import org.junit.Before;
import org.junit.Test;
import org.next.ws.core.game.Game;
import org.next.ws.core.game.player.Player;
import org.next.ws.core.game.player.SinglePlayerCamp;
import org.next.ws.core.game.player.deck.Deck;
import org.next.ws.core.hero.healer.Healer;
import org.next.ws.core.hero.hunter.Hunter;
import org.next.ws.gamelauncher.broadcaster.ConsoleBroadCaster;

public class GameLauncherTest {

    private GameLauncher gameLauncher;

    @Before
    public void setUp() throws Exception {
        Deck deck = new Deck("[1,2,3,4,5]");
        Deck deck2 = new Deck("[1,2,3,4,5]");
        Game game = new Game(new SinglePlayerCamp(new Player(new Healer("사제"), deck)), new SinglePlayerCamp(new Player(new Hunter("냥꾼"), deck2)));
        gameLauncher = new GameLauncher(game, new ConsoleBroadCaster());
    }

    @Test
    public void test(){
        gameLauncher.start();
    }

}