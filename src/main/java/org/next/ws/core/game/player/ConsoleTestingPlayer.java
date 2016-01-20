package org.next.ws.core.game.player;

import org.next.ws.core.game.GameEvent;
import org.next.ws.core.game.player.deck.Deck;
import org.next.ws.core.hero.Hero;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsoleTestingPlayer extends Player {


    private static final Logger logger = LoggerFactory.getLogger(ConsoleTestingPlayer.class);

    public ConsoleTestingPlayer(Hero hero, Deck deck) {
        super(hero, deck);

    }

    @Override
    public void broadCast(GameEvent type, Object result) {
        logger.debug(type.toString() + result);
    }
}
