package org.next.ws.core.game.player;

import lombok.Getter;
import lombok.Setter;
import org.next.ws.core.fighter.FighterDto;
import org.next.ws.core.game.player.hero.HeroDto;

import java.util.List;

@Getter
@Setter
public class EnemyPlayerDto {
    private HeroDto hero;
    private List<FighterDto> field;
    private int handSize;
    private int deckSize;

    public EnemyPlayerDto(Player player) {
        this.hero = player.getGameHero().getHeroDto();
        this.handSize = player.getHand().countCard();
        this.field = player.getField().getFighterDtoList();
        this.deckSize = player.getDeck().countCard();
    }
}
