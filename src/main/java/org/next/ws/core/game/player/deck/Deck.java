package org.next.ws.core.game.player.deck;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.next.ws.core.card.Card;
import org.next.ws.core.card.factory.cardFactory;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class Deck {
    private List<Card> cards;

    public Deck(String cardListString) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Integer> cardIdList = objectMapper.readValue(cardListString, List.class);
        validate(cardIdList);
        this.cards = cardIdList.stream().map(cardFactory::getNewCardById).collect(Collectors.toList());
    }


    private void validate(List<Integer> cardIdList) {
    }

    public void shuffle() {
    }
}
