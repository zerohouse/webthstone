package org.next.ws.web.deck;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.next.ws.web.card.CardEntityDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class DeckEntityDto {

    private List<CardEntityDto> cards = new ArrayList<>();

    private Long id;

    private String name;

    public DeckEntityDto(DeckEntity deck) {
        this.id = deck.getId();
        this.name = deck.getName();
        cards = deck.getDeckHasCardList().stream().map(CardEntityDto::new).collect(Collectors.toList());
    }
}
