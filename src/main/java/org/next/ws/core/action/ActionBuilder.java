package org.next.ws.core.action;

import org.next.ws.core.action.impl.ManaAddAction;
import org.next.ws.core.action.impl.RandomVitalAction;
import org.next.ws.core.action.impl.TargetAssassinateAction;
import org.next.ws.core.action.impl.VitalAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

class ActionBuilder {

    private static final Logger logger = LoggerFactory.getLogger(ActionBuilder.class);

    private static Map<Integer, Class<? extends Action>> actionMap = new HashMap<>();

    static {
        actionMap.put(0, ManaAddAction.class);
        actionMap.put(1, RandomVitalAction.class);
        actionMap.put(2, TargetAssassinateAction.class);
        actionMap.put(3, VitalAction.class);
    }


    static Action getAction(String actionString) {
        try {
            if (!actionString.contains("|")) {
                return (Action) actionMap.get(Integer.parseInt(actionString)).getDeclaredConstructor(String.class).newInstance("");
            }
            String[] actionStringSplit = actionString.split("|");
            Class<? extends Action> actionClass = actionMap.get(Integer.parseInt(actionStringSplit[0]));
            return (Action) actionClass.getDeclaredConstructor(String.class).newInstance(actionStringSplit[1]);

        } catch (Exception e) {
            logger.debug("파라미터 형식이 잘못되었거나, 컨스트럭터 구현이 잘못되었습니다.", actionString);
            e.printStackTrace();
            return null;
        }
    }
}
