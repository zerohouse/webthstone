package org.next.ws.web.fighter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.next.ws.web.action.ActionEntity;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class FighterHasAction {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    ActionEntity actionEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    FighterEntity fighterEntity;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public FighterHasAction(FighterEntity fighterEntity, ActionEntity actionEntity) {
        this.fighterEntity = fighterEntity;
        this.actionEntity = actionEntity;
    }
}
