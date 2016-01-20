package org.next.ws.core.fighter;

import org.next.ws.core.action.Action;
import org.next.ws.core.fighter.property.AttackPower;
import org.next.ws.core.fighter.property.Vital;

public class FieldFighter extends Fighter {
    public FieldFighter(FighterTemplate fighterTemplate) {
        super(new AttackPower(fighterTemplate.getAttack()), new Vital(fighterTemplate.getVital()), fighterTemplate.getImg(), fighterTemplate.getName());
        this.deathActionList = Action.getNonTargetActionList(fighterTemplate.getDeathAction());
        this.turnStartActionList = Action.getNonTargetActionList(fighterTemplate.getTurnStartAction());
        this.turnEndActionList = Action.getNonTargetActionList(fighterTemplate.getTurnEndAction());
        this.AttackActionList = Action.getNonTargetActionList(fighterTemplate.getAttackAction());
        this.HeatedActionList = Action.getNonTargetActionList(fighterTemplate.getHeatedAction());
    }

    @Override
    public void die() {
        deathActionList.forEach(deathAction->{
            deathAction.act(camp);
        });
        camp.removeFighter(this);
    }
}
