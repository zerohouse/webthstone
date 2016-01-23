package org.next.ws.web.deck;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.next.ws.web.card.CardEntity;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class DeckHasCard {

    @ManyToOne
    @JoinColumn
    CardEntity card;

    @ManyToOne
    @JoinColumn
    DeckEntity deck;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
