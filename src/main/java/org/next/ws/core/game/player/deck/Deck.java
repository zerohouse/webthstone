package org.next.ws.core.game.player.deck;

import lombok.Getter;
import org.next.ws.core.card.Card;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Stack;

@Getter
public class Deck {
    private Stack<Card> cards = new Stack<>();;

    public Deck(List<Card> cards){
        this.cards.addAll(cards);
    }

//    public void validate(Integer size, Integer sameCardLimit) {
//        if (!size.equals(countCard()))
//            throw new CardSizeNotMatched();
//        if (cards.stream().filter(fbId -> Collections.frequency(cards, fbId) > sameCardLimit).findAny().isPresent())
//            throw new SameCardLimitViolation();
//    }

    public void shuffle() {
        Collections.shuffle(cards, new Random());
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