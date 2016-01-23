package org.next.ws.core.card;

import lombok.Getter;
import lombok.ToString;
import org.next.ws.core.action.Action;
import org.next.ws.core.card.exception.CardUnUsableException;
import org.next.ws.core.card.property.Cost;
import org.next.ws.core.fighter.FieldFighter;
import org.next.ws.core.fighter.Fighter;
import org.next.ws.core.fighter.FighterTemplate;
import org.next.ws.core.game.Game;
import org.next.ws.core.game.player.Player;
import org.next.ws.core.scanner.ComponentScanner;

import java.util.List;

@ToString
@Getter
public class Card {
    Cost cost;
    String name;
    Action useAction;
    FighterTemplate fighterTemplate;
    String desc;
    String img;
    Integer cardIdInGame;

    public Card(CardTemplate cardTemplate) {
        this.cost = new Cost(cardTemplate.getCost());
        this.name = cardTemplate.getName();
        this.desc = cardTemplate.getDesc();
        this.img = cardTemplate.getImg();
        useAction = ComponentScanner.getAction(cardTemplate.getUseActionTemplate());

        if (cardTemplate.isFighterCard())
            this.fighterTemplate = cardTemplate.getFighterTemplate();
    }

    private void usableCheck(Player player, List<Fighter> targetList) throws CardUnUsableException {
        if (!player.hasEnoughMana(cost))
            throw new CardUnUsableException("마나가 부족합니다.");
        if (!player.addAble())
            throw new CardUnUsableException("필드가 꽉차서 사용할 수 없습니다.");
        if (useAction != null)
            useAction.able(player, targetList);
    }

    public void use(Player player, List<Fighter> targetList) throws CardUnUsableException {
        usableCheck(player, targetList);
        if (useAction != null)
            useAction.act(player, targetList);
        if (this.fighterTemplate != null) {
            player.addFighter(new FieldFighter(fighterTemplate, player));
        }
        player.useMana(cost);

        player.getHand().useCard(this);
        player.getGame().gameStateUpdate();
    }


    public void generateCardIdInGame(Game game) {
        if (game == null)
            return;
        cardIdInGame = game.getNextId();
    }
}
