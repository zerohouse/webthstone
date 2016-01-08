package org.next.ws.core.fighter;

import org.next.ws.core.game.camp.Camp;
import org.next.ws.core.hero.prop.vital.Vital;

import java.util.function.Consumer;

public abstract class Fighter {

    protected AttackPower attackPower;
    protected Vital vital;
    protected MagicPower magicPower;

    private Consumer<Camp> deathAction;
    private Consumer<Camp> turnStartAction;
    private Consumer<Camp> turnEndAction;
    private Consumer<Camp> heroPowerUseAction;

}
