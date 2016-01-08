package org.next.ws.core.game.player.hero;

public class Mana {

    private static final Integer MAX_MANA = 10;
    private static final Integer MIN_MANA = 0;

    private Integer mana;

    Mana() {
        this.mana = 0;
    }

    public void add(int size) {
        this.mana += size;
        if (mana > MAX_MANA)
            mana = MAX_MANA;
        if (mana < MIN_MANA)
            mana = MIN_MANA;
    }
}
