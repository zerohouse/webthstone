package org.next.ws.core.fighter;

public interface FighterTemplate {
    String getName();

    String getImg();

    Integer getVital();

    Integer getAttack();

    String getDeathAction();

    String getTurnStartAction();

    String getTurnEndAction();

    String getAttackAction();

    String getHeatedAction();
}
