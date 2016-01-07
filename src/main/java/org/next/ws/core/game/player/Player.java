package org.next.ws.core.game.player;


import lombok.Getter;
import lombok.ToString;
import org.next.ws.core.card.Card;
import org.next.ws.core.game.field.Field;
import org.next.ws.core.game.player.deck.Deck;
import org.next.ws.core.game.player.hand.Hand;
import org.next.ws.core.game.player.secret.Secret;
import org.next.ws.core.game.player.hero.GameHero;
import org.next.ws.core.hero.Hero;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


@ToString
@Getter
public class Player {

    private static final Logger logger = LoggerFactory.getLogger(Player.class);

    public Player(Hero hero, Deck deck) {
        this.gameHero = new GameHero(hero);
        this.secret = new ArrayList<>();
        this.deck = deck;
        this.hand = new Hand();
        this.field = new Field();
        nullDeckCount = 0;
    }

    private final Deck deck;
    private final Hand hand;
    private final GameHero gameHero;

    private final Field field;
    private final List<Secret> secret;
    private int nullDeckCount;

    public void ready(boolean first) {


    }

    public void drawCard(Integer size) {
        for (int i = 0; i < size; i++) {
            drawCard();
        }
    }

    private void drawCard() {
        Card card = deck.drawCard();
        if (card != null) {
            hand.addCard(card);
            return;
        }
        this.nullCardEvent();
    }

    public void nullCardEvent() {
        nullDeckCount++;
        logger.debug("player deck empty {}", nullDeckCount);
    }

}
