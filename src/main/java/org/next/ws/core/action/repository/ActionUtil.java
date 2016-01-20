package org.next.ws.core.action.repository;

import org.next.ws.core.action.Action;
import org.next.ws.core.action.serialize.WsAction;
import org.next.ws.core.action.serialize.WsActionProperties;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ActionUtil {

    private static Map<String, Constructor<?>> constructorMap = new ConcurrentHashMap<>();
    private static Map<String, WsActionProperties> InfoMap = new ConcurrentHashMap<>();

    static {
        Reflections reflections = new Reflections(new MethodAnnotationsScanner(), new SubTypesScanner());
        constructorMap = new HashMap<>();
        InfoMap = new HashMap<>();
        reflections.getConstructorsAnnotatedWith(WsAction.class).forEach(constructor -> {
            WsAction wsAction = (WsAction) constructor.getAnnotation(WsAction.class);
            constructorMap.put(wsAction.id(), constructor);
            InfoMap.put(wsAction.id(), new WsActionProperties(wsAction));
        });
    }

    public static WsActionProperties getActionInfo(String id){
        return InfoMap.get(id);
    }

    public static Constructor<?> getConstructor(String id){
        return constructorMap.get(id);
    }

    public static Map<String, WsActionProperties> getActionInfo() {
        return InfoMap;
    }
}
