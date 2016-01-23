package org.next.ws.web.deck;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.next.ws.core.card.Card;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class DeckEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "deck")
    private List<DeckHasCard> deckHasCardList = new ArrayList<>();

    public List<Card> getCardList() {
        return deckHasCardList.stream().map(deckHasCard -> new Card(deckHasCard.getCard())).collect(Collectors.toList());
    }
}
