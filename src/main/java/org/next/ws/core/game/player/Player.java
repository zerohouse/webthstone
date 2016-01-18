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
import org.next.ws.core.game.player.hero.HeroFighter;
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
        this.gameHero = new HeroFighter(hero);
        this.secret = new ArrayList<>();
        this.deck = deck;
        this.hand = new Hand(camp);
        this.field = new Field();
        this.nullDeckCount = 0;
    }

    final Deck deck;
    final Hand hand;
    final HeroFighter gameHero;


    private final List<Secret> secret;
    private int nullDeckCount;

    public void useCard(Integer id, List<Fighter> targetList) {
        try {
            Card card = this.hand.pickCard(id);
            card.use(camp, targetList);
            camp.getEnemy().broadCast(GameEventType.ENEMY_USE_CARD, card.getName());
            broadCastEvent(GameEventType.USE_CARD, card.getName());
            logger.debug("카드사용 {}", card);
        } catch (CardNotExistException e) {
            logger.debug("카드불가 사용 불가");
            broadCastEvent(GameEventType.WARN, "잘못된 접근입니다. 손에 없는 카드를 쓰려고 했습니다.");
        } catch (CardUnUsableException e) {
            logger.debug("카드불가 사용 불가 {}", e.getMessage());
            broadCastEvent(GameEventType.WARN, e.getMessage());
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
        gameHero.addVital(-nullDeckCount);
        broadCastEvent(GameEventType.CARD_DECK_NULL, nullDeckCount);
        logger.debug("player deck empty - count:{}", nullDeckCount);
    }

    public int countCard() {
        return deck.countCard() + hand.countCard();
    }

    public boolean hasEnoughMana(Cost cost) {
        return gameHero.getMana().isEnough(cost.getCost());
    }

    public abstract void broadCastEvent(GameEventType type, Object result);

    public void broadCastEvent(GameEventType type) {
        broadCastEvent(type, null);
    }

    public boolean addAble() {
        return field.addAble();
    }

    public void addFighter(Fighter fighter) {
        field.addFighter(fighter);
    }

    public void generateCardIdInGame(Game game) {
        deck.getCards().forEach(card -> card.generateCardIdInGame(game));
        hand.getCards().forEach(card -> card.generateCardIdInGame(game));
    }

    public void useMana(Cost cost) {
        gameHero.getMana().add(-cost.getCost());
    }

    public void win() {
        broadCastEvent(GameEventType.WIN);
    }

    public void lose() {
        broadCastEvent(GameEventType.LOSE);
    }

    public List<Fighter> resolveTargetList(List<Integer> targetList) {
        return null;
    }
}
