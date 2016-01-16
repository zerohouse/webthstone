package org.next.ws.core.game.player;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.next.ws.core.card.Card;
import org.next.ws.core.card.exception.CardNotExistException;
import org.next.ws.core.card.exception.CardUnUsableException;
import org.next.ws.core.card.property.Cost;
import org.next.ws.core.event.standard.GameEventType;
import org.next.ws.core.fighter.Fighter;
import org.next.ws.core.game.Game;
import org.next.ws.core.game.camp.Camp;
import org.next.ws.core.game.field.Field;
import org.next.ws.core.game.player.deck.Deck;
import org.next.ws.core.game.player.hand.Hand;
import org.next.ws.core.game.player.hero.GameHero;
import org.next.ws.core.game.player.secret.Secret;
import org.next.ws.core.hero.Hero;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


@ToString(exclude = "camp")
@Getter
@Setter
public abstract class Player {

    private static final Logger logger = LoggerFactory.getLogger(Player.class);
    private Camp camp;
    protected Field field;

    public Player(Hero hero, Deck deck) {
        this.gameHero = new GameHero(hero);
        this.secret = new ArrayList<>();
        this.deck = deck;
        this.hand = new Hand(camp);
        this.field = new Field();
        this.nullDeckCount = 0;
        this.turn = false;
    }

    final Deck deck;
    final Hand hand;
    final GameHero gameHero;


    private final List<Secret> secret;
    private int nullDeckCount;
    private boolean turn;

    public void useCard(Integer id) {
        try {
            Card card = this.hand.pickCard(id);
            card.use(camp);
            this.hand.useCard(card);
            logger.debug("카드사용 {}", card);
        } catch (CardNotExistException e) {
            logger.debug("카드불가 사용 불가");
            broadCastEvent(GameEventType.ERROR, "잘못된 접근입니다. 손에 없는 카드를 쓰려고 했습니다.");
        } catch (CardUnUsableException e) {
            logger.debug("카드불가 사용 불가 {}", e.getMessage());
            broadCastEvent(GameEventType.ERROR, e.getMessage());
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

    public boolean hasEnoughMana(Cost cost) {
        return gameHero.getMana().isEnough(cost.getCost());
    }

    public abstract void broadCastEvent(GameEventType type, Object result);

    public boolean addAble(List<Fighter> fighters) {
        return field.addAble(fighters);
    }

    public void addFighters(List<Fighter> fighters) {
        field.addFighters(fighters);
    }

    public void generateCardIdInGame(Game game) {
        deck.getCards().forEach(card -> card.generateCardIdInGame(game));
        hand.getCards().forEach(card -> card.generateCardIdInGame(game));
    }
}
