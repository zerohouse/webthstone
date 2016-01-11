package org.next.ws.core.game.camp;

import lombok.Getter;
import lombok.ToString;
import org.next.ws.cards.SpellCard;
import org.next.ws.core.card.Card;
import org.next.ws.core.event.EventRunner;
import org.next.ws.core.fighter.Fighter;
import org.next.ws.core.event.BroadCaster;
import org.next.ws.core.game.player.Player;

import java.util.ArrayList;
import java.util.List;

@ToString
public class SinglePlayerCamp extends Camp {

    Player player;

    public SinglePlayerCamp(Player player) {
        this.player = player;
        player.setCamp(this);
    }


    @Override
    public void setEventRunner(EventRunner eventRunner) {
        this.eventRunner = eventRunner;
    }

    @Override
    public void setBroadCaster(BroadCaster broadCaster) {
        this.broadCaster = broadCaster;
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
    protected void turnStartAction() {
        player.getGameHero().manaAdd(1);
        player.drawCard(1);
    }

    @Override
    protected void turnEndAction() {

    }

    @Override
    public String getName() {
        return player.getGameHero().getName();
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
        return field.getFighters();
    }

}
