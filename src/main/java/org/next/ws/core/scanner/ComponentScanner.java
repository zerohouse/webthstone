package org.next.ws.core.scanner;

import org.next.ws.core.action.Action;
import org.next.ws.core.action.NonTargetAction;
import org.next.ws.core.action.serialize.ActionTemplate;
import org.next.ws.core.action.serialize.WsAction;
import org.next.ws.core.action.serialize.WsActionProperties;
import org.next.ws.core.game.player.Player;
import org.next.ws.core.hero.Hero;
import org.next.ws.core.hero.WsHero;
//import org.reflections.Reflections;
//import org.reflections.scanners.MethodAnnotationsScanner;
//import org.reflections.scanners.SubTypesScanner;
//import org.reflections.scanners.TypeAnnotationsScanner;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ComponentScanner {

    //register Actions
    private static Map<String, Constructor<?>> constructorMap = new ConcurrentHashMap<>();
    private static Map<String, WsActionProperties> InfoMap = new ConcurrentHashMap<>();

    static {
//        Reflections reflections = new Reflections(new MethodAnnotationsScanner(), new SubTypesScanner());
//        constructorMap = new HashMap<>();
//        InfoMap = new HashMap<>();
//        reflections.getConstructorsAnnotatedWith(WsAction.class).forEach(constructor -> {
//            WsAction wsAction = (WsAction) constructor.getAnnotation(WsAction.class);
//            constructorMap.put(wsAction.id(), constructor);
//            InfoMap.put(wsAction.id(), new WsActionProperties(wsAction));
//        });
    }

    public static Map<String, WsActionProperties> getActionInfo() {
        return InfoMap;
    }

    public static Action getAction(ActionTemplate actionTemplate) {
        if (actionTemplate == null)
            return null;
        if (actionTemplate.getTemplateId() == null)
            return null;
        try {
            Class<?>[] parameterTypes = constructorMap.get(actionTemplate.getTemplateId()).getParameterTypes();
            Object[] params = actionTemplate.getParams();
            return (Action) constructorMap.get(actionTemplate.getTemplateId()).newInstance(actionTemplate.getParams());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static NonTargetAction getNonTargetAction(ActionTemplate actionTemplate) {
        try {
            return (NonTargetAction) constructorMap.get(actionTemplate.getTemplateId()).newInstance(actionTemplate.getParams());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<NonTargetAction> getActionList(List<ActionTemplate> actionTemplateList) {
        List<NonTargetAction> result = new ArrayList<>();
        actionTemplateList.forEach(actionTemplate -> {
            result.add(getNonTargetAction(actionTemplate));
        });
        return result;
    }


    // register Heros
    private static Map<String, Class<?>> heroMap = new ConcurrentHashMap<>();

    static {
//        Reflections reflections = new Reflections(new TypeAnnotationsScanner(), new SubTypesScanner());
//        reflections.getTypesAnnotatedWith(WsHero.class).forEach(clazz -> heroMap.put(clazz.getAnnotation(WsHero.class).value(), clazz));
    }


    public static Hero newHero(String heroType, String name, String img, Player player) {
        try {
            return (Hero) heroMap.get(heroType).getConstructor(String.class, String.class).newInstance(name, img);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
}
