package org.next.ws.core.game.player.deck;

import lombok.Getter;
import lombok.ToString;
import org.next.ws.cards.CardFactory;
import org.next.ws.core.card.Card;
import org.next.ws.core.game.Game;
import org.next.ws.util.Util;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Stack;

@Getter
@ToString
public class Deck {
    private Stack<Card> cards;
    private List<Integer> cardIdList;

    public Deck(String cardListString) throws IOException {
        cardIdList = Util.OBJECT_MAPPER.readValue(cardListString, List.class);
        cards = new Stack<>();
        cardIdList.forEach(id -> {
            cards.add(CardFactory.getNewCardByTemplateId(id));
        });
    }


    public void validate(Integer size, Integer sameCardLimit) {
        if (!size.equals(countCard()))
            throw new CardSizeNotMatched();
        if (cardIdList.stream().filter(id -> Collections.frequency(cardIdList, id) > sameCardLimit).findAny().isPresent())
            throw new SameCardLimitViolation();
    }

    public void shuffle() {
        long seed = System.nanoTime();
        Collections.shuffle(cards, new Random(seed));
    }

    public int countCard() {
        return cards.size();
    }

    public Card drawCard() {
        if (cards.size() == 0)
            return null;
        return cards.pop();
    }

}