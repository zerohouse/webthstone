package org.next.ws.core.card;

public interface CardTemplate {

    Integer getCost();

    String getImg();

    String getName();

    String getDesc();

    boolean isFighter();

    Integer getAttack();

    Integer getVital();

    String getActionString();
}
