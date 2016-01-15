package org.next.ws.core.game.player.hero;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HeroDto {

    private int maxVital;
    private int vital;
    private int attack;
    private String name;

    public HeroDto(GameHero gameHero) {
        this.maxVital = gameHero.getVital().getMaxVital();
        this.vital = gameHero.getVital().getVital();
        this.name = gameHero.getName();
    }
}
