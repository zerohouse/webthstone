package org.next.ws.web.user;

import io.vertx.ext.web.handler.sockjs.SockJSSocket;
import org.next.ws.jeo.resolver.JeoParameterResolver;
import org.next.ws.jeo.resolver.ParameterResolver;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Parameter;
import java.util.Map;

@JeoParameterResolver
public class UserResolver implements ParameterResolver {

    @Autowired
    UserMap userMap;

    @Override
    public boolean isAcceptable(Parameter parameter, Map<String, Object> params, SockJSSocket sockJSSocket, String paramName) {
        return parameter.getType().equals(User.class);
    }

    @Override
    public Object resolve(Parameter parameter, Map<String, Object> params, SockJSSocket sockJSSocket, String paramName) {
        return userMap.getUser(sockJSSocket);
    }

}
