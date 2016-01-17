package org.next.ws.core.game.player.hero;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManaDto {

    private int maxMana;
    private int mana;

    public ManaDto(Mana mana) {
        this.mana = mana.mana;
        this.maxMana = mana.maxMana;
    }
}
