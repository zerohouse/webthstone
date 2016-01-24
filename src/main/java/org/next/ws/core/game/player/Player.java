package org.next.ws.core.game.player;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.next.ws.core.StaticValues;
import org.next.ws.core.card.Card;
import org.next.ws.core.card.StaticCardTemplate;
import org.next.ws.core.card.exception.CardNotExistException;
import org.next.ws.core.card.exception.CardUnUsableException;
import org.next.ws.core.card.property.Cost;
import org.next.ws.core.fighter.FieldFighter;
import org.next.ws.core.fighter.Fighter;
import org.next.ws.core.game.Game;
import org.next.ws.core.game.event.Event;
import org.next.ws.core.game.event.EventType;
import org.next.ws.core.game.field.Field;
import org.next.ws.core.game.player.deck.Deck;
import org.next.ws.core.game.player.hand.Hand;
import org.next.ws.core.game.player.hero.HeroFighter;
import org.next.ws.core.game.player.secret.Secret;
import org.next.ws.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;


@ToString(exclude = "enemy")
@Getter
@Setter
public abstract class Player {

    public Player() {
        this.secret = new ArrayList<>();
        this.hand = new Hand();
        this.field = new Field();
        this.nullDeckCount = 0;
    }

    private static final Logger logger = LoggerFactory.getLogger(Player.class);
    protected Field field;
    protected Game game;
    protected boolean turn;
    protected Timer timer;

    protected Deck deck;
    protected HeroFighter gameHero;
    protected final Hand hand;
    protected Player enemy;

    protected final List<Secret> secret;
    protected int nullDeckCount;


    public void useCard(Integer id, List<Fighter> targetList) {
        try {
            Card card = this.hand.pickCard(id);
            card.use(this, targetList);
            enemy.broadCast(EventType.ENEMY_USE_CARD, card.getName());
            broadCast(EventType.USE_CARD, card.getName());
            logger.debug("카드사용 {}", card);
        } catch (CardNotExistException e) {
            logger.debug("카드불가 사용 불가");
            broadCast(EventType.WARN, "잘못된 접근입니다. 손에 없는 카드를 쓰려고 했습니다.");
        } catch (CardUnUsableException e) {
            logger.debug("카드불가 사용 불가 {}", e.getMessage());
            broadCast(EventType.WARN, e.getMessage());
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
        sendToClient(new Event(EventType.CARD_DECK_NULL, nullDeckCount));
        logger.debug("player deck empty - count:{}", nullDeckCount);
    }

    public int countCard() {
        return deck.countCard() + hand.countCard();
    }

    public boolean hasEnoughMana(Cost cost) {
        return gameHero.getMana().isEnough(cost.getCost());
    }

    public abstract void sendToClient(Event event);

    public boolean addAble() {
        return field.addAble();
    }

    public void addFighter(FieldFighter fighter) {
        field.addFighter(fighter);
    }

    public void removeFighter(FieldFighter fieldFighter) {
        field.removeFighter(fieldFighter);
    }

    public void generateCardIdInGame(Game game) {
        deck.getCards().forEach(card -> card.generateCardIdInGame(game));
        hand.getCards().forEach(card -> card.generateCardIdInGame(game));
    }

    public void useMana(Cost cost) {
        gameHero.getMana().add(-cost.getCost());
    }

    public void win() {
        broadCast(EventType.WIN);
    }

    public void lose() {
        broadCast(EventType.LOSE);
        enemy.win();
        game.end();
    }

    public List<Fighter> resolveTargetList(List<Integer> targetList) {
        return null;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void ready(boolean first) {
        deck.shuffle();
        if (first) {
            drawCard(3);
            return;
        }
        drawCard(4);
        Card card = new Card(StaticCardTemplate.COIN);
        card.generateCardIdInGame(game);
        hand.addCard(card);
    }


    public void startTurn() {
        logger.debug("{} 턴을 시작합니다.", gameHero.getName());
        gameHero.startTurn();
        field.startTurn();
        drawCard(1);
        this.turn = true;
        broadCast(EventType.START_TURN);
        enemy.broadCast(EventType.ENEMY_TURN);
        gameStateUpdate();

        timer = Util.setTimeout(() -> {
            if (turn) endTurn();
        }, StaticValues.TURN_TIME_OUT);
    }

    public void endTurn() {
        if (!turn)
            return;
        timer.cancel();
        logger.debug("{} 턴을 마칩니다.", gameHero.getName());
        this.turn = false;
        field.endTurn();
        this.enemy.startTurn();
        sendToClient(new Event(EventType.END_TURN));
        gameStateUpdate();
    }

    public void broadCast(EventType type) {
        Event event = new Event(type);
        field.broadCast(event);
        sendToClient(event);
    }

    public void broadCast(EventType type, Object obj) {
        Event event = new Event(type, obj);
        field.broadCast(event);
        sendToClient(event);
    }

    public void gameStateUpdate() {
        broadCast(EventType.PLAYER_UPDATE, new PlayerDto(this));
        broadCast(EventType.ENEMY_UPDATE, new EnemyPlayerDto(enemy));
    }


    public Fighter getFighterById(Integer id) {
        if (id.equals(StaticValues.MY_GAME_HERO_ID))
            return getGameHero();
        if (id.equals(StaticValues.ENEMY_GAME_HERO_ID))
            return enemy.getGameHero();
        return getAllFieldFighters().stream().filter(fieldFighter -> id.equals(fieldFighter.getId())).findAny().get();
    }


    public List<FieldFighter> getAllFieldFighters() {
        List<FieldFighter> result = new ArrayList<>();
        result.addAll(field.getFighters());
        result.addAll(enemy.field.getFighters());
        return result;
    }

    public List<FieldFighter> getFieldFighters() {
        return field.getFighters();
    }

    public List<Fighter> getFighters() {
        List<Fighter> result = new ArrayList<>();
        result.addAll(field.getFighters());
        result.add(gameHero);
        return result;
    }

    public List<? extends Fighter> getAllFighters() {
        List<Fighter> result = new ArrayList<>();
        result.addAll(field.getFighters());
        result.addAll(enemy.field.getFighters());
        result.add(gameHero);
        result.add(enemy.gameHero);
        return result;
    }

    public void attack(Integer by, Integer target) {
        Fighter attacker = getFighterById(by);
        Fighter defender = getFighterById(target);
        attacker.attack(defender);
        broadCast(EventType.ATTACK, "공격 : " + attacker.getName() + " -> " + defender.getName());
        getGame().gameStateUpdate();
    }
}
