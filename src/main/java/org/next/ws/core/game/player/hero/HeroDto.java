package org.next.ws.core.game.player.hero;

import lombok.Getter;
import lombok.Setter;
import org.next.ws.core.fighter.property.VitalDto;

@Getter
@Setter
public class HeroDto {

    private ManaDto mana;
    private VitalDto vital;
    private int attack;
    private String name;

    public HeroDto(HeroFighter gameHero) {
        this.vital = new VitalDto(gameHero.getVital());
        this.name = gameHero.getName();
        this.mana = new ManaDto(gameHero.getMana());
    }
}
