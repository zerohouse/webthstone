package org.next.ws.web.jeo;

import io.vertx.ext.web.handler.sockjs.SockJSSocket;
import org.next.ws.util.Util;
import org.next.ws.web.jeo.user.User;
import org.next.ws.web.jeo.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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
    }

    public void execute(String input, User user) {
        try {
            Jeo jeo = Util.OBJECT_MAPPER.readValue(input, Jeo.class); // 디펜던시 해결
            Exe exe = jeoEvents.get(jeo.getType());
            if (exe == null) {
                logger.warn("event:{} is not declared", jeo.getType());
                return;
            }
            exe.execute(jeo.getType(), jeo.getParams(), user);

        } catch (IOException e) {
            logger.warn("{} is not convertible", input);
        }
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

        public void execute(String type, Map<String, Object> params, User user) {
            try {
                Object result = method.invoke(instance, getParameters(method, params, user));
                if (result == null)
                    return;
                String event = "".equals(makeEvent) ? type : makeEvent;
                Jeo.event(user.getSockJSSocket(), event, result);
            } catch (IllegalAccessException | InvocationTargetException e) {
                logger.warn("error occurred when execute method");
                e.printStackTrace();
            }
        }

        private Object[] getParameters(Method method, Map<String, Object> params, User user) {
            Parameter[] parameters = method.getParameters();

            List<Object> parameterList = new ArrayList<>();
            for (int i = 0; i < parameters.length; i++) {
                Parameter parameter = parameters[i];
                parameterList.add(getParameter(parameter, params, user, paramNames[i]));
            }
            return parameterList.toArray();
        }


        /*
        * 파라미터 핸들링
        * */
        private Object getParameter(Parameter parameter, Map<String, Object> params, User user, String paramName) {
            if (params != null && parameter.getType().isAssignableFrom(params.getClass()))
                return params;
            if (parameter.getType().isAssignableFrom(User.class))
                return user;
            if (params != null && parameter.getType().isAssignableFrom(params.get(paramName).getClass()))
                return params.get(paramName);
            return null;
        }
    }
}
