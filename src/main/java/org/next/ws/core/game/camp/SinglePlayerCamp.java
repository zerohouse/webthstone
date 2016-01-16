package org.next.ws.core.game.camp;

import lombok.ToString;
import org.next.ws.cards.SpellCard;
import org.next.ws.core.StaticValues;
import org.next.ws.core.action.Action;
import org.next.ws.core.card.Card;
import org.next.ws.core.event.standard.GameEventType;
import org.next.ws.core.fighter.Fighter;
import org.next.ws.core.game.player.EnemyPlayerDto;
import org.next.ws.core.game.player.Player;
import org.next.ws.core.game.player.PlayerDto;
import org.next.ws.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@ToString
public class SinglePlayerCamp extends Camp {

    private static final Logger logger = LoggerFactory.getLogger(SinglePlayerCamp.class);

    Player player;

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
        player.getHand().addCard(new Card(SpellCard.COIN));
    }


    @Override
    public String getName() {
        return player.getGameHero().getName();
    }

    @Override
    public void startTurn() {
        logger.debug("{} 턴을 시작합니다.", player.getGameHero().getName());
        startTurnEffects.forEach(Action::act);
        player.getGameHero().manaAdd(1);
        player.drawCard(1);
        this.turn = true;
        broadCast(GameEventType.START_TURN);
        enemy.broadCast(GameEventType.ENEMY_TURN);
        gameStateUpdate();

        Util.setTimeout(() -> {
            if (turn) endTurn();
        }, StaticValues.TURN_TIME_OUT);

    }

    @Override
    public void endTurn() {
        logger.debug("{} 턴을 마칩니다.", player.getGameHero().getName());
        endTurnEffects.forEach(Action::act);
        this.turn = false;
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
