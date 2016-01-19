package org.next.ws.core.card;

import lombok.Getter;
import lombok.ToString;
import org.next.ws.core.StaticValues;
import org.next.ws.core.action.Action;
import org.next.ws.core.card.exception.CardUnUsableException;
import org.next.ws.core.card.property.Cost;
import org.next.ws.core.fighter.FieldFighter;
import org.next.ws.core.fighter.Fighter;
import org.next.ws.core.fighter.property.AttackPower;
import org.next.ws.core.fighter.property.Vital;
import org.next.ws.core.game.Game;
import org.next.ws.core.game.camp.Camp;
import org.next.ws.core.game.player.Player;

import java.util.List;

@ToString
@Getter
public class Card {
    Cost cost;
    String name;
    Action useAction;
    Fighter fighter;
    String desc;
    String img;
    Integer cardIdInGame;

    public Card(CardTemplate cardTemplate) {
        this.cost = new Cost(cardTemplate.getCost());
        this.name = cardTemplate.getName();
        this.desc = cardTemplate.getDesc();
        this.img = cardTemplate.getImg();
        if (cardTemplate.isFighter())
            this.fighter = new FieldFighter(new AttackPower(cardTemplate.getAttack()), new Vital(cardTemplate.getVital()), this.img, this.name);
        else
            useAction = Action.getAction(cardTemplate.getActionString());
    }

    private void usableCheck(Camp camp, List<Fighter> targetList) throws CardUnUsableException {
        if (!camp.getPlayingPlayer().hasEnoughMana(cost))
            throw new CardUnUsableException("마나가 부족합니다.");
        if (!camp.getPlayingPlayer().addAble())
            throw new CardUnUsableException("필드가 꽉차서 사용할 수 없습니다.");
        if (useAction != null)
            useAction.ableCheck(camp, targetList);
    }

    public void use(Camp camp, List<Fighter> targetList) throws CardUnUsableException {
        usableCheck(camp, targetList);
        if (useAction != null)
            useAction.act(camp, targetList);
        Player player = camp.getPlayingPlayer();
        if (this.fighter != null) {
            fighter.setCamp(camp);
            fighter.getAttackPower().setCount(StaticValues.DEFAULT_ATTACK_COUNT_WHEN_PLAYED);
            camp.addFighter(fighter);
        }
        player.useMana(cost);

        player.getHand().useCard(this);
        camp.getGame().gameStateUpdate();
    }


    public void generateCardIdInGame(Game game) {
        if (game == null)
            return;
        cardIdInGame = game.getNextId();
        if (this.fighter != null)
            fighter.setId(game.getNextId());
    }
}
