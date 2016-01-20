package org.next.ws.jeo.resolver;

import io.vertx.ext.web.handler.sockjs.SockJSSocket;

import java.lang.reflect.Parameter;
import java.util.Map;

public interface ParameterResolver {
    boolean isAcceptable(Parameter parameter, Map<String, Object> params, SockJSSocket sockJSSocket, String paramName) throws Exception;

    Object resolve(Parameter parameter, Map<String, Object> params, SockJSSocket sockJSSocket, String paramName) throws Exception;
}
