package org.next.ws.web.deck;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.next.ws.core.card.Card;
import org.next.ws.web.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class DeckEntity {

    @ManyToOne
    @JoinColumn
    private User user;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "deck")
    private List<DeckHasCard> deckHasCardList = new ArrayList<>();

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    public List<Card> getCardList() {
        return deckHasCardList.stream().map(deckHasCard -> new Card(deckHasCard.getCard())).collect(Collectors.toList());
    }
}
