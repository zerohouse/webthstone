package org.next.ws.core.game.player;


import lombok.Getter;
import lombok.ToString;
import org.next.ws.core.game.field.Field;
import org.next.ws.core.game.player.deck.Deck;
import org.next.ws.core.game.player.hand.Hand;
import org.next.ws.core.game.player.secret.Secret;
import org.next.ws.core.game.player.hero.GameHero;
import org.next.ws.core.hero.Hero;

import java.util.ArrayList;
import java.util.List;


@ToString
@Getter
public class Player {

    public Player(Hero hero, Deck deck) {
        this.gameHero = new GameHero(hero);
        this.secret = new ArrayList<>();
        this.deck = deck;
        this.hand = new Hand();
        this.field = new Field();
    }

    private final Deck deck;
    private final Hand hand;
    private final GameHero gameHero;

    private final Field field;
    private final List<Secret> secret;

}
