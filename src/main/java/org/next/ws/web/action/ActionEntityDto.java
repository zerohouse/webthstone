package org.next.ws.web.action;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.next.ws.web.card.CardEntity;
import org.next.ws.web.fighter.FighterHasAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ActionEntityDto {

    private Long id;
    private String timing;
    private String templateId;
    private String paramString;

    public ActionEntityDto(ActionEntity actionTemplate) {
        this.id = actionTemplate.getId();
        this.templateId = actionTemplate.getTemplateId();
        this.paramString = actionTemplate.getParamString();
        if (actionTemplate.getTiming() != null)
            this.timing = actionTemplate.getTiming().toString();
    }
}
