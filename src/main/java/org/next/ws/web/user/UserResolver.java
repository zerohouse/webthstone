package org.next.ws.web.user;

import io.vertx.ext.web.handler.sockjs.SockJSSocket;
import org.next.ws.web.jeo.resolver.JeoParameterResolver;
import org.next.ws.web.jeo.resolver.ParameterResolver;
import org.next.ws.web.user.User;
import org.next.ws.web.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Parameter;
import java.util.Map;

@JeoParameterResolver
public class UserResolver implements ParameterResolver {

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean isAcceptable(Parameter parameter, Map<String, Object> params, SockJSSocket sockJSSocket, String paramName) {
        return parameter.getType().equals(User.class);
    }

    @Override
    public Object resolve(Parameter parameter, Map<String, Object> params, SockJSSocket sockJSSocket, String paramName) {
        return userRepository.getUser(sockJSSocket);
    }

}
