package org.next.ws.jeo;

import io.vertx.ext.web.handler.sockjs.SockJSSocket;
import org.next.ws.jeo.resolver.*;
import org.next.ws.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class JeoEventResolver {

    private static final Logger logger = LoggerFactory.getLogger(JeoEventResolver.class);

    @Autowired
    ApplicationContext applicationContext;

    private final ConcurrentHashMap<String, Exe> jeoEvents;

    private ParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();

    public JeoEventResolver() {
        jeoEvents = new ConcurrentHashMap<>();
    }

    private List<ParameterResolver> parameterResolvers;

    @PostConstruct
    public void build() {
        Map<String, Object> controllers = applicationContext.getBeansWithAnnotation(JeoController.class);
        controllers.forEach((name, instance) -> {
            Method[] methods = instance.getClass().getDeclaredMethods();
            for (Method method : methods) {
                if (!method.isAnnotationPresent(JeoEvent.class))
                    continue;
                JeoEvent jeo = method.getAnnotation(JeoEvent.class);
                jeoEvents.put(jeo.value(), new Exe(instance, method, jeo.eventDest()));
            }
        });

        parameterResolvers = new ArrayList<>();
        parameterResolvers.add(new DefaultNameResolver());
        parameterResolvers.add(new DefaultMapResolver());
        parameterResolvers.add(new JsonParseResolver());

        Map map = applicationContext.getBeansWithAnnotation(JeoParameterResolver.class);
        map.forEach((s, o) -> {
            if (ParameterResolver.class.isAssignableFrom(ParameterResolver.class))
                parameterResolvers.add((ParameterResolver) o);
        });

    }

    public void execute(String input, SockJSSocket sockJSSocket) throws Exception {
        logger.debug("socket data received {}", input);
        Jeo jeo = Util.OBJECT_MAPPER.readValue(input, Jeo.class); // 디펜던시 해결
        Exe exe = jeoEvents.get(jeo.getType());
        if (exe == null) {
            logger.warn("event:{} is not declared", jeo.getType());
            return;
        }
        exe.execute(jeo.getType(), jeo.getParams(), sockJSSocket);
    }

    private class Exe {
        private Object instance;
        private Method method;
        private String makeEvent;
        private String[] paramNames;

        public Exe(Object instance, Method method, String makeEvent) {
            this.instance = instance;
            this.method = method;
            this.makeEvent = makeEvent;
            this.paramNames = parameterNameDiscoverer.getParameterNames(method);
        }

        public void execute(String type, Map<String, Object> params, SockJSSocket sockJSSocket) throws Exception {
            Object[] parameters = getParameters(method, params, sockJSSocket);
            Object result = method.invoke(instance, parameters);
            if (result == null)
                return;
            String event = "".equals(makeEvent) ? type : makeEvent;
            Jeo.event(sockJSSocket, event, result);
        }

        private Object[] getParameters(Method method, Map<String, Object> params, SockJSSocket sockJSSocket) throws Exception {
            Parameter[] parameters = method.getParameters();

            List<Object> parameterList = new ArrayList<>();
            for (int i = 0; i < parameters.length; i++) {
                Parameter parameter = parameters[i];
                parameterList.add(getParameter(parameter, params, sockJSSocket, paramNames[i]));
            }
            return parameterList.toArray();
        }


        /*
        * 파라미터 핸들링
        * */
        private Object getParameter(Parameter parameter, Map<String, Object> params, SockJSSocket sockJSSocket, String paramName) throws Exception {
            for (ParameterResolver parameterResolver : parameterResolvers) {
                if (parameterResolver.isAcceptable(parameter, params, sockJSSocket, paramName))
                    return parameterResolver.resolve(parameter, params, sockJSSocket, paramName);
            }
            return null;
        }
    }
}
