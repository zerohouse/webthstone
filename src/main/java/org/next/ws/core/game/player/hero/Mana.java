package org.next.ws.core.game.player.hero;

public class Mana {

    private static final Integer MAX_MANA = 10;
    private static final Integer MIN_MANA = 0;

    int mana;
    int maxMana;

    public Mana() {
        this.mana = 0;
        this.maxMana = 0;
    }

    public void add(int size) {
        this.mana += size;
    }

    public boolean isEnough(int cost) {
        return mana >= cost;
    }

    public void increaseMaxMana(int size) {
        this.maxMana += size;
        if (maxMana > MAX_MANA)
            maxMana = MAX_MANA;
        if (maxMana < MIN_MANA)
            maxMana = MIN_MANA;
    }

    public void repair() {
        this.mana = maxMana;
    }
}
