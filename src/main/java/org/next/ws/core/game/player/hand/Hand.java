package org.next.ws.core.game.player.hand;

import lombok.Getter;
import lombok.ToString;
import org.next.ws.core.StaticValues;
import org.next.ws.core.card.Card;
import org.next.ws.core.card.CardDto;
import org.next.ws.core.card.exception.CardNotExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ToString
@Getter
public class Hand {

    private static final Logger logger = LoggerFactory.getLogger(Hand.class);

    private int maxSize;
    private int burnedSize;
    private List<Card> cards;

    public Hand() {
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

    public Card pickCard(Integer id) throws CardNotExistException {
        return cards.stream().filter(card -> card.getCardIdInGame().equals(id)).findAny().get();
    }

    public List<CardDto> getCardDtoList() {
        return cards.stream().map(CardDto::new).collect(Collectors.toList());
    }

    public void useCard(Card card) {
        cards.remove(card);
    }
}
