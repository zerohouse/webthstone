package org.next.ws.core.game.player;

import lombok.Getter;
import lombok.Setter;
import org.next.ws.core.card.CardDto;
import org.next.ws.core.fighter.FighterDto;
import org.next.ws.core.game.player.hero.HeroDto;

import java.util.List;

@Getter
@Setter
public class PlayerDto {

    private HeroDto hero;
    private List<FighterDto> field;
    private List<CardDto> hand;
    private int deckSize;

    public PlayerDto(Player player) {
        this.hero = player.getGameHero().getHeroDto();
        this.hand = player.getHand().getCardDtoList();
        this.field = player.getField().getFighterDtoList();
        this.deckSize = player.getDeck().countCard();
    }
}
