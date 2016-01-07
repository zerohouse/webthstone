package org.next.ws.core.game.player.hand;

import lombok.Getter;
import lombok.ToString;
import org.next.ws.core.ConfigValues;
import org.next.ws.core.card.Card;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
public class Hand {

    private static final Logger logger = LoggerFactory.getLogger(Hand.class);

    private int maxSize;
    private int burnedSize;
    private List<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
        maxSize = ConfigValues.DEFAULT_HAND_MAX_SIZE;
        burnedSize = 0;
    }

    public void addCard(Card card) {
        if (cards.size() >= maxSize) {
            burnedSize++;
            logger.debug("player hand has no space burned:{} maxSize:{}", burnedSize, maxSize);
            return;
        }
        cards.add(card);
    }

    public int countCard() {
        return cards.size();
    }
}
