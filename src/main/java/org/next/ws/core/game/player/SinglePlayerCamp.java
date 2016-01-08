package org.next.ws.core.game.player;

import lombok.ToString;
import org.next.ws.cards.SpellCard;
import org.next.ws.core.card.Card;
import org.next.ws.core.game.camp.Camp;

@ToString
public class SinglePlayerCamp extends Camp {

    Player player;

    public SinglePlayerCamp(Player player) {
        this.player = player;
        player.setCamp(this);
    }

    @Override
    public void ready(boolean first) {
        player.deck.shuffle();
        if (first) {
            player.drawCard(3);
            return;
        }
        player.drawCard(4);
        player.hand.addCard(new Card(SpellCard.COIN));
    }

    @Override
    protected void turnStartAction() {
        player.gameHero.manaAdd(1);
        player.drawCard(1);
    }

    @Override
    protected void turnEndAction() {

    }

    @Override
    public String getName() {
        return player.gameHero.getName();
    }

}
