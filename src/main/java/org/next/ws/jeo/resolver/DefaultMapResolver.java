package org.next.ws.jeo.resolver;

import io.vertx.ext.web.handler.sockjs.SockJSSocket;

import java.lang.reflect.Parameter;
import java.util.Map;

public class DefaultMapResolver implements ParameterResolver {
    @Override
    public boolean isAcceptable(Parameter parameter, Map<String, Object> params, SockJSSocket sockJSSocket, String paramName) {
        return params != null && parameter.getType().isAssignableFrom(params.getClass());
    }

    @Override
    public Object resolve(Parameter parameter, Map<String, Object> params, SockJSSocket sockJSSocket, String paramName) {
        return params;
    }
}
