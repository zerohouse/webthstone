package org.next.ws.core.game.player.hand;

import lombok.Getter;
import lombok.ToString;
import org.next.ws.core.StaticValues;
import org.next.ws.core.card.Card;
import org.next.ws.core.card.UnUsableCardException;
import org.next.ws.core.game.camp.Camp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@ToString(exclude = {"camp"})
@Getter
public class Hand {

    private final Camp camp;

    private static final Logger logger = LoggerFactory.getLogger(Hand.class);

    private int maxSize;
    private int burnedSize;
    private List<Card> cards;

    public Hand(Camp camp) {
        this.camp = camp;
        this.maxSize = StaticValues.DEFAULT_HAND_MAX_SIZE;
        cards = new ArrayList<>();
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

    public Card pickCard(Integer index) throws UnUsableCardException {
        Card card = cards.get(index);
        if (!card.usable(camp)) {
            throw new UnUsableCardException();
        }
        cards.remove(card);
        return card;
    }
}
