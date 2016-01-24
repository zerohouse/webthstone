package org.next.ws.core.card;

import org.next.ws.core.action.serialize.ActionTemplate;
import org.next.ws.core.fighter.FighterTemplate;
import org.next.ws.core.game.event.EventType;

public class StaticCardTemplate {
    public static final CardTemplate COIN = new CardTemplate() {
        @Override
        public Integer getCost() {
            return 0;
        }

        @Override
        public String getImg() {
            return "/resources/card/coin.png";
        }

        @Override
        public String getName() {
            return "동전";
        }

        @Override
        public String getDesc() {
            return "이번 턴에 마나를 1 얻습니다.";
        }

        @Override
        public boolean isFighterCard() {
            return false;
        }

        @Override
        public FighterTemplate getFighterTemplate() {
            return null;
        }

        @Override
        public ActionTemplate getUseActionTemplate() {
                return new ActionTemplate() {
                @Override
                public String getTemplateId() {
                    return "manaadd";
                }

                @Override
                public EventType getTiming() {
                    return null;
                }

                @Override
                public Object[] getParams() {
                    return new Object[]{"1"};
                }
            };
        }
    };
}
