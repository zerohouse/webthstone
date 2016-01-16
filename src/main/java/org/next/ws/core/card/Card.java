package org.next.ws.core.card;

import lombok.Getter;
import lombok.ToString;
import org.next.ws.core.action.Action;
import org.next.ws.core.card.exception.CardUnUsableException;
import org.next.ws.core.card.property.Cost;
import org.next.ws.core.fighter.Fighter;
import org.next.ws.core.game.Game;
import org.next.ws.core.game.camp.Camp;

import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
public class Card {
    private Cost cost;
    private String name;
    private Action useAction;
    private List<Fighter> fighters;
    private String desc;
    private String img;
    private Integer cardIdInGame;

    public Card(CardTemplate cardTemplate) {
        this.cost = new Cost(cardTemplate.getCost());
        this.name = cardTemplate.getName();
        this.desc = cardTemplate.getDesc();
        this.img = cardTemplate.getImg();
        this.fighters = new ArrayList<>();
    }

    private void usableCheck(Camp camp) throws CardUnUsableException {
        if (!camp.getPlayingPlayer().hasEnoughMana(cost))
            throw new CardUnUsableException("마나가 부족합니다.");
        if (!camp.getPlayingPlayer().addAble(fighters))
            throw new CardUnUsableException("필드가 꽉차서 사용할 수 없습니다.");
        if (!useAction.isActable())
            throw new CardUnUsableException("사용 조건이 맞지 않습니다.");
    }

    public void use(Camp camp) throws CardUnUsableException {
        usableCheck(camp);
        useAction.act();
        camp.getPlayingPlayer().addFighters(fighters);
    }


    public void generateCardIdInGame(Game game) {
        cardIdInGame = game.getNextCardId();
    }
}
