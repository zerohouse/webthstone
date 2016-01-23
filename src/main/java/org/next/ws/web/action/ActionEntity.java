package org.next.ws.web.action;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.next.ws.core.StaticValues;
import org.next.ws.core.action.serialize.ActionTemplate;
import org.next.ws.core.game.event.EventType;
import org.next.ws.web.card.CardEntity;
import org.next.ws.web.fighter.FighterHasAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class ActionEntity implements ActionTemplate {

    public ActionEntity(ActionParameterDto effect) {
        this.timing = effect.getTiming();
        this.templateId = effect.getEffect();
        this.paramString = "";
        if (effect.getParams() == null)
            return;
        for (int i = 0; i < effect.getParams().size(); i++) {
            if (i != 0)
                paramString += StaticValues.DELIMITER;
            paramString += effect.getParams().get(i);
        }

    }


    @OneToMany(mappedBy = "actionEntity")
    private List<FighterHasAction> fighterHasActions = new ArrayList<>();

    @OneToMany(mappedBy = "actionTemplate")
    private List<CardEntity> cardEntities = new ArrayList<>();

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String timing;

    @Column
    private String templateId;

    @Column
    private String paramString;


    @Override
    public EventType getTiming() {
        try {
            return EventType.valueOf(timing);
        } catch (Exception e) {
            return null;
        }
    }


    @Override
    public Object[] getParams() {
        return paramString.split(StaticValues.DELIMITER);
    }
}
