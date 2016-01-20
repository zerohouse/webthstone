package org.next.ws.web.card;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;
import org.next.ws.core.card.CardTemplate;
import org.next.ws.web.fighter.FighterEntity;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class CardEntity implements CardTemplate {

    @ManyToOne
    @JoinColumn
    FighterEntity fighterTemplate;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Integer cost;

    @Column
    private String img;

    @Column
    private String name;

    @Column
    private String desc;

    @Type(type = "org.hibernate.type.NumericBooleanType")
    @Column
    private boolean fighter;

    @Column
    private String actionString;


}
