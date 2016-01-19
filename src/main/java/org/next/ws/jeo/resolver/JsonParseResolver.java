package org.next.ws.jeo.resolver;

import io.vertx.ext.web.handler.sockjs.SockJSSocket;
import org.next.ws.util.Util;

import java.lang.reflect.Parameter;
import java.util.Map;

public class JsonParseResolver implements ParameterResolver {
    @Override
    public boolean isAcceptable(Parameter parameter, Map<String, Object> params, SockJSSocket sockJSSocket, String paramName) {
        return parameter != null && params != null && parameter.isAnnotationPresent(JsonParse.class);
    }

    @Override
    public Object resolve(Parameter parameter, Map<String, Object> params, SockJSSocket sockJSSocket, String paramName) {
        String path = parameter.getAnnotation(JsonParse.class).value();
        if("".equals(path))
            return Util.OBJECT_MAPPER.convertValue(params, parameter.getType());
        return Util.OBJECT_MAPPER.convertValue(params.get(path), parameter.getType());
    }
}
