package org.next.ws.web.matching;

import org.next.ws.core.event.standard.GameEventType;
import org.next.ws.core.game.player.Player;
import org.next.ws.core.game.player.deck.Deck;
import org.next.ws.core.hero.Hero;
import org.next.ws.web.jeo.Jeo;
import org.next.ws.web.jeo.user.User;

public class SocketUserPlayer extends Player {
    private User user;
    private static final String prefix = "game.";

    public SocketUserPlayer(User user, Hero hero, Deck deck) {
        super(hero, deck);
        this.user = user;
    }

    @Override
    public void broadCastEvent(GameEventType type, Object result) {
        Jeo.event(user.getSockJSSocket(), prefix + type.toString().toLowerCase(), result);
    }
}
