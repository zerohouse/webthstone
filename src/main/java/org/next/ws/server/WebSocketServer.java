package org.next.ws.server;


import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.sockjs.BridgeOptions;
import io.vertx.ext.web.handler.sockjs.PermittedOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Component
public class WebSocketServer extends AbstractVerticle {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketServer.class);


    @Autowired
    ApplicationContext applicationContext;

    @Override
    public void start() throws Exception {
        Router router = Router.router(vertx);
        BridgeOptions bridgeOptions = new BridgeOptions();
        EventBus eventBus = vertx.eventBus();

        applicationContext.getBeansWithAnnotation(SocketHandler.class).forEach((key, instance) ->
                registerHandlerClass(eventBus, bridgeOptions, instance)
        );

        bridgeOptions.addOutboundPermitted(new PermittedOptions().setAddress("chat.to.client"));


        SockJSHandler sockJSHandler = SockJSHandler.create(vertx).bridge(bridgeOptions);
        router.route("/eventbus/*").handler(sockJSHandler);

        vertx.createHttpServer().requestHandler(router::accept).listen(8081);

    }

    private void registerHandlerClass(EventBus eventBus, BridgeOptions bridgeOptions, Object socketHandler) {
        String basePath = "";
        String className = socketHandler.getClass().getSimpleName();
        if (socketHandler.getClass().isAnnotationPresent(Handle.class)) {
            basePath += socketHandler.getClass().getAnnotation(Handle.class).value();
        }
        for (Field field : socketHandler.getClass().getDeclaredFields()) {
        }
        for (Method method : socketHandler.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(Handle.class)) {
                String path = basePath + method.getAnnotation(Handle.class).value();
                bridgeOptions.addInboundPermitted(new PermittedOptions().setAddress(path));
                eventBus.consumer(path).handler(message -> {
                    invokeMethod(eventBus, socketHandler, method, message);
                });
                logger.debug("'{}' request mapped {}.{}", path, className, method.getName());
            }
        }
    }


    private void invokeMethod(EventBus eventBus, Object socketHandler, Method method, Message<Object> message) {
        try {
            Class<?>[] parameterTypes = method.getParameterTypes();
            method.invoke(socketHandler, getParameters(eventBus, message, parameterTypes));
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private Object[] getParameters(EventBus eventBus, Message<Object> message, Class<?>[] parameterTypes) {
        List<Object> parameters = new ArrayList<>();
        for (Class<?> parameterType : parameterTypes) {
            if (parameterType.equals(EventBus.class)) {
                parameters.add(eventBus);
                continue;
            }
            if (parameterType.equals(Message.class)) {
                parameters.add(message);
                continue;
            }
            parameters.add(null);
        }
        return parameters.toArray();
    }

}
