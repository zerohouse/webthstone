package org.next.ws.core.game.camp;

import lombok.ToString;
import org.next.ws.core.StaticValues;
import org.next.ws.core.card.Card;
import org.next.ws.core.card.UseCardTemplate;
import org.next.ws.core.event.standard.GameEventType;
import org.next.ws.core.fighter.FieldFighter;
import org.next.ws.core.fighter.Fighter;
import org.next.ws.core.game.Game;
import org.next.ws.core.game.player.EnemyPlayerDto;
import org.next.ws.core.game.player.Player;
import org.next.ws.core.game.player.PlayerDto;
import org.next.ws.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

@ToString
public class SinglePlayerCamp extends Camp {

    private static final Logger logger = LoggerFactory.getLogger(SinglePlayerCamp.class);

    Player player;
    Timer timer;

    public SinglePlayerCamp(Player player) {
        this.player = player;
        player.setCamp(this);
    }

    @Override
    public void ready(boolean first) {
        player.getDeck().shuffle();
        if (first) {
            player.drawCard(3);
            return;
        }
        player.drawCard(4);
        Card card = new Card(UseCardTemplate.COIN);
        card.generateCardIdInGame(game);
        player.getHand().addCard(card);
    }


    @Override
    public String getName() {
        return player.getGameHero().getName();
    }

    @Override
    public void startTurn() {
        logger.debug("{} 턴을 시작합니다.", player.getGameHero().getName());
        startTurnEffects.forEach(action -> {
            action.act(this, null);
        });
        player.getGameHero().startTurn();
        player.getField().startTurn();
        player.drawCard(1);
        this.turn = true;
        broadCast(GameEventType.START_TURN);
        enemy.broadCast(GameEventType.ENEMY_TURN);
        gameStateUpdate();

        timer = Util.setTimeout(() -> {
            if (turn) endTurn();
        }, StaticValues.TURN_TIME_OUT);
    }

    @Override
    public void endTurn() {
        if (!turn)
            return;
        timer.cancel();
        logger.debug("{} 턴을 마칩니다.", player.getGameHero().getName());
        endTurnEffects.forEach(action -> {
            action.act(this, null);
        });
        this.turn = false;
        player.getField().endTurn();
        this.enemy.startTurn();
        broadCast(GameEventType.END_TURN);
        gameStateUpdate();
    }

    @Override
    public void broadCast(GameEventType gameEvent, Object result) {
        player.broadCastEvent(gameEvent, result);
    }

    @Override
    protected void broadCast(GameEventType gameEvent) {
        broadCast(gameEvent, null);
    }

    @Override
    public void gameStateUpdate() {
        player.broadCastEvent(GameEventType.PLAYER_UPDATE, new PlayerDto(player));
        player.broadCastEvent(GameEventType.ENEMY_UPDATE, new EnemyPlayerDto(enemy.getPlayingPlayer()));
    }

    @Override
    public void generateCardIdInGame(Game game) {
        player.generateCardIdInGame(game);
    }

    @Override
    public void removeFighter(FieldFighter fieldFighter) {
        player.getField().removeFighter(fieldFighter);
    }

    @Override
    public void addFighter(Fighter fighter) {
        player.getField().addFighter(fighter);
    }

    @Override
    public void lose() {
        this.player.lose();
    }

    @Override
    public void win() {
        this.player.win();
    }

    @Override
    public Player getPlayingPlayer() {
        return player;
    }

    @Override
    public List<Fighter> getAllFighters() {
        List<Fighter> fighters = new ArrayList<>();
        fighters.addAll(getFieldFighters());
        fighters.add(player.getGameHero());
        return fighters;
    }

    @Override
    public List<Fighter> getFieldFighters() {
        return player.getField().getFighters();
    }

}
