package org.next.ws.jeo.resolver;

import io.vertx.ext.web.handler.sockjs.SockJSSocket;

import java.lang.reflect.Parameter;
import java.util.Map;

public class SockJSSocketResolver implements ParameterResolver {
    @Override
    public boolean isAcceptable(Parameter parameter, Map<String, Object> params, SockJSSocket sockJSSocket, String paramName) throws Exception {
        return parameter.getType().isAssignableFrom(SockJSSocket.class);
    }

    @Override
    public Object resolve(Parameter parameter, Map<String, Object> params, SockJSSocket sockJSSocket, String paramName) throws Exception {
        return sockJSSocket;
    }
}
