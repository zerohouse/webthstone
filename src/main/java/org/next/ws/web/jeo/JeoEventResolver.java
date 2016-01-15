package org.next.ws.web.jeo;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.handler.sockjs.SockJSSocket;
import org.next.ws.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
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

    public void execute(String input, SockJSSocket sockJSSocket) {
        try {
            Jeo jeo = Util.OBJECT_MAPPER.readValue(input, Jeo.class); // 디펜던시 해결
            Exe exe = jeoEvents.get(jeo.getType());
            if (exe == null) {
                logger.warn("event:{} is not declared", jeo.getType());
                return;
            }
            exe.execute(jeo.getType(), jeo.getParams(), sockJSSocket);

        } catch (IOException e) {
            logger.warn("{} is not convertible", input);
        }
    }

    private class Exe {
        private Object instance;
        private Method method;
        private String makeEvent;

        public Exe(Object instance, Method method, String makeEvent) {
            this.instance = instance;
            this.method = method;
            this.makeEvent = makeEvent;
        }

        public void execute(String type, Map<String, Object> params, SockJSSocket sockJSSocket) {
            try {
                Object result = method.invoke(instance, getParameters(method, params, sockJSSocket));
                if (result == null)
                    return;
                String event = "".equals(makeEvent) ? type : makeEvent;
                Jeo jeo = new Jeo(event, result);
                sockJSSocket.write(Buffer.buffer(Util.OBJECT_MAPPER.writeValueAsString(jeo)));
            } catch (IllegalAccessException | InvocationTargetException | JsonProcessingException e) {
                logger.warn("error occurred when execute method");
                e.printStackTrace();
            }
        }

        private Object[] getParameters(Method method, Map<String, Object> params, SockJSSocket sockJSSocket) {
            Parameter[] parameters = method.getParameters();
            List<Object> parameterList = new ArrayList<>();
            for (Parameter parameter : parameters) {
                parameterList.add(getParameter(parameter, params, sockJSSocket));
            }
            return parameterList.toArray();
        }

        private Object getParameter(Parameter parameter, Map<String, Object> params, SockJSSocket sockJSSocket) {
            if (params != null && parameter.getType().isAssignableFrom(params.getClass()))
                return params;
            return null;
        }
    }
}
