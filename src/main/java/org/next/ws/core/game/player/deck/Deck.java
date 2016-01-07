package org.next.ws.core.game.player.deck;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.ToString;
import org.next.ws.core.card.Card;
import org.next.ws.core.card.factory.CardFactory;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Stack;

@ToString
public class Deck {
    private Stack<Card> cards;

    public Deck(String cardListString) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Integer> cardIdList = objectMapper.readValue(cardListString, List.class);
        validate(cardIdList);
        cards = new Stack<>();
        cardIdList.forEach(id -> {
            cards.add(CardFactory.getNewCardById(id));
        });
    }


    private void validate(List<Integer> cardIdList) {
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
