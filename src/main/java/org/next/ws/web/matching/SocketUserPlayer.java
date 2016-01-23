package org.next.ws.web.matching;

import lombok.Getter;
import org.next.ws.core.game.event.Event;
import org.next.ws.core.game.player.Player;
import org.next.ws.core.game.player.deck.Deck;
import org.next.ws.core.game.player.hero.HeroFighter;
import org.next.ws.core.scanner.ComponentScanner;
import org.next.ws.jeo.Jeo;
import org.next.ws.web.user.User;

@Getter
public class SocketUserPlayer extends Player {
    private User user;
    private static final String prefix = "game.";

    public SocketUserPlayer(User user, String heroType, Deck deck) {
        this.user = user;
        setGameHero(new HeroFighter(ComponentScanner.newHero(heroType, user.getName(), user.getImg(), this)));
        setDeck(deck);
    }

    @Override
    public void sendToClient(Event event) {
        Jeo.event(user.getSockJSSocket(), prefix + event.getType().toString().toLowerCase(), event.getInfo());
    }

}
