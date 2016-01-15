package org.next.ws.web.matching;

import io.vertx.ext.web.handler.sockjs.SockJSSocket;
import org.next.ws.core.event.standard.GameEventType;
import org.next.ws.core.game.player.Player;
import org.next.ws.core.game.player.deck.Deck;
import org.next.ws.core.hero.Hero;
import org.next.ws.web.jeo.Jeo;

public class SocketPlayer extends Player {
    private SockJSSocket sockJSSocket;
    private static final String prefix = "game.";

    public SocketPlayer(SockJSSocket sockJSSocket, Hero hero, Deck deck) {
        super(hero, deck);
        this.sockJSSocket = sockJSSocket;
    }

    @Override
    public void broadCastEvent(GameEventType type, Object result) {
        Jeo.event(sockJSSocket, prefix + type.toString().toLowerCase(), result);
    }
}
