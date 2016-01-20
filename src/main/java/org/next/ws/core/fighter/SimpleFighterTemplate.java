package org.next.ws.core.fighter;

import lombok.Getter;

@Getter
public class SimpleFighterTemplate implements FighterTemplate {


    private String name;
    private String img;
    private Integer attack;
    private Integer vital;

    public SimpleFighterTemplate(String name, String img, Integer attack, Integer vital) {
        this.name = name;
        this.img = img;
        this.attack = attack;
        this.vital = vital;
    }

    @Override
    public String getDeathAction() {
        return null;
    }

    @Override
    public String getTurnStartAction() {
        return null;
    }

    @Override
    public String getTurnEndAction() {
        return null;
    }

    @Override
    public String getAttackAction() {
        return null;
    }

    @Override
    public String getHeatedAction() {
        return null;
    }
}
