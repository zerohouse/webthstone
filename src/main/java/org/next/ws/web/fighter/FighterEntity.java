package org.next.ws.web.fighter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.next.ws.core.action.serialize.ActionTemplate;
import org.next.ws.core.fighter.FighterTemplate;
import org.next.ws.web.action.ActionEntity;
import org.next.ws.web.card.CardEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class FighterEntity implements FighterTemplate {

    public FighterEntity(FighterParameterDto fighter, CardEntity cardEntity) {
        this.name =cardEntity.getName();
        this.img = cardEntity.getImg();
        this.vital = fighter.getVital();
        this.attack = fighter.getAttack();
        fighter.getEffects().forEach(effect->{
            ActionEntity actionEntity = new ActionEntity(effect);
            FighterHasAction fighterHasAction = new FighterHasAction(this, actionEntity);
            this.fighterHasActions.add(fighterHasAction);
        });
    }

    @OneToMany(mappedBy = "fighterTemplate")
    List<CardEntity> cardEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "fighterEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<FighterHasAction> fighterHasActions = new ArrayList<>();

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



    @Override
    public List<ActionTemplate> getActionList() {
        return fighterHasActions.stream().map(FighterHasAction::getActionEntity).collect(Collectors.toList());
    }
}
