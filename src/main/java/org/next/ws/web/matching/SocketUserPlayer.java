package org.next.ws.web.matching;

import org.next.ws.core.game.GameEvent;
import org.next.ws.core.game.player.Player;
import org.next.ws.core.game.player.deck.Deck;
import org.next.ws.core.hero.Hero;
import org.next.ws.jeo.Jeo;
import org.next.ws.web.user.User;

public class SocketUserPlayer extends Player {
    private User user;
    private static final String prefix = "game.";

    public SocketUserPlayer(User user, Hero hero, Deck deck) {
        super(hero, deck);
        this.user = user;
    }

    @Override
    public void broadCast(GameEvent type, Object result) {
        Jeo.event(user.getSockJSSocket(), prefix + type.toString().toLowerCase(), result);
    }
}
