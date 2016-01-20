package org.next.ws.core.game.player;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.next.ws.core.StaticValues;
import org.next.ws.core.action.NonTargetAction;
import org.next.ws.core.card.Card;
import org.next.ws.core.card.StaticCardTemplate;
import org.next.ws.core.card.exception.CardNotExistException;
import org.next.ws.core.card.exception.CardUnUsableException;
import org.next.ws.core.card.property.Cost;
import org.next.ws.core.fighter.FieldFighter;
import org.next.ws.core.fighter.Fighter;
import org.next.ws.core.game.Game;
import org.next.ws.core.game.GameEvent;
import org.next.ws.core.game.field.Field;
import org.next.ws.core.game.player.deck.Deck;
import org.next.ws.core.game.player.hand.Hand;
import org.next.ws.core.game.player.hero.HeroFighter;
import org.next.ws.core.game.player.secret.Secret;
import org.next.ws.core.hero.Hero;
import org.next.ws.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;


@ToString(exclude = "enemy")
@Getter
@Setter
public abstract class Player {

    public Player(Hero hero, Deck deck) {
        this.gameHero = new HeroFighter(hero);
        this.secret = new ArrayList<>();
        this.deck = deck;
        this.hand = new Hand();
        this.field = new Field();
        this.nullDeckCount = 0;
    }

    private static final Logger logger = LoggerFactory.getLogger(Player.class);
    protected Field field;
    protected Game game;
    protected boolean turn;
    protected Timer timer;

    protected final Deck deck;
    protected final Hand hand;
    protected final HeroFighter gameHero;
    protected Player enemy;

    protected Map<GameEvent, List<NonTargetAction>> events = new HashMap<>();

    protected final List<Secret> secret;
    protected int nullDeckCount;

    public void executeEvent(GameEvent event) {
        if (events.get(event) == null)
            return;
        events.get(event).forEach(e -> {
            e.act(this);
        });
    }

    public void bind(GameEvent event, NonTargetAction action) {
        if (events.get(event) == null)
            events.put(event, new ArrayList<>());
        events.get(event).add(action);
    }

    public void unbind(GameEvent event, NonTargetAction action) {
        if (events.get(event) == null)
            return;
        events.get(event).remove(action);
    }

    public void useCard(Integer id, List<Fighter> targetList) {
        try {
            Card card = this.hand.pickCard(id);
            card.use(this, targetList);
            enemy.broadCast(GameEvent.ENEMY_USE_CARD, card.getName());
            broadCast(GameEvent.USE_CARD, card.getName());
            logger.debug("카드사용 {}", card);
        } catch (CardNotExistException e) {
            logger.debug("카드불가 사용 불가");
            broadCast(GameEvent.WARN, "잘못된 접근입니다. 손에 없는 카드를 쓰려고 했습니다.");
        } catch (CardUnUsableException e) {
            logger.debug("카드불가 사용 불가 {}", e.getMessage());
            broadCast(GameEvent.WARN, e.getMessage());
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
            executeEvent(GameEvent.DRAW_CARD);
            hand.addCard(card);
            return;
        }
        this.nullCardEvent();
    }

    public void nullCardEvent() {
        nullDeckCount++;
        gameHero.addVital(-nullDeckCount);
        broadCast(GameEvent.CARD_DECK_NULL, nullDeckCount);
        logger.debug("player deck empty - count:{}", nullDeckCount);
    }

    public int countCard() {
        return deck.countCard() + hand.countCard();
    }

    public boolean hasEnoughMana(Cost cost) {
        return gameHero.getMana().isEnough(cost.getCost());
    }

    public abstract void broadCast(GameEvent type, Object result);

    public void broadCast(GameEvent type) {
        broadCast(type, null);
    }

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
        broadCast(GameEvent.WIN);
    }

    public void lose() {
        broadCast(GameEvent.LOSE);
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
        executeEvent(GameEvent.START_TURN);
        gameHero.startTurn();
        field.startTurn();
        drawCard(1);
        this.turn = true;
        broadCast(GameEvent.START_TURN);
        enemy.broadCast(GameEvent.ENEMY_TURN);
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
        executeEvent(GameEvent.END_TURN);
        this.turn = false;
        field.endTurn();
        this.enemy.startTurn();
        broadCast(GameEvent.END_TURN);
        gameStateUpdate();
    }

    public void gameStateUpdate() {
        broadCast(GameEvent.PLAYER_UPDATE, new PlayerDto(this));
        broadCast(GameEvent.ENEMY_UPDATE, new EnemyPlayerDto(enemy));
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
}
