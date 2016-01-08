package org.next.ws.core.game.player;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.next.ws.core.card.Card;
import org.next.ws.core.card.UnUsableCardException;
import org.next.ws.core.game.camp.Camp;
import org.next.ws.core.game.player.deck.Deck;
import org.next.ws.core.game.player.hand.Hand;
import org.next.ws.core.game.player.secret.Secret;
import org.next.ws.core.game.player.hero.GameHero;
import org.next.ws.core.hero.Hero;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


@ToString(exclude = "camp")
@Getter
@Setter
public class Player {

    private static final Logger logger = LoggerFactory.getLogger(Player.class);
    private Camp camp;

    public Player(Hero hero, Deck deck) {
        this.gameHero = new GameHero(hero);
        this.secret = new ArrayList<>();
        this.deck = deck;
        this.hand = new Hand(camp);
        this.nullDeckCount = 0;
        this.turn = false;
    }

    final Deck deck;
    final Hand hand;
    final GameHero gameHero;


    private final List<Secret> secret;
    private int nullDeckCount;
    private boolean turn;

    public void useCard(Integer index){
        try {
            Card card = this.hand.pickCard(index);
            card.use(camp);
        } catch (UnUsableCardException e) {
            e.printStackTrace();
        }
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
        logger.debug("player deck empty - count:{}", nullDeckCount);
    }

    public int countCard() {
        return deck.countCard() + hand.countCard();
    }
}
