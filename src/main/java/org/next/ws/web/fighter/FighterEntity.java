package org.next.ws.web.fighter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.next.ws.core.fighter.FighterTemplate;
import org.next.ws.web.card.CardEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class FighterEntity implements FighterTemplate {

    @OneToMany(mappedBy = "fighterTemplate")
    List<CardEntity> cardEntityList = new ArrayList<>();

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column
    private String name;

    @Column
    private String img;

    @Column
    private Integer vital;

    @Column
    private Integer attack;

    @Column
    private String deathAction;

    @Column
    private String turnStartAction;

    @Column
    private String turnEndAction;

    @Column
    private String attackAction;

    @Column
    private String heatedAction;
}
