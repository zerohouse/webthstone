package org.next.ws.web.jeo.user;

import io.vertx.ext.web.handler.sockjs.SockJSSocket;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class UserRepository {

    ConcurrentHashMap<String, User> userMap = new ConcurrentHashMap<>();

    public User getUser(SockJSSocket sockJSSocket) {
        String id = sockJSSocket.writeHandlerID();
        User user = userMap.get(id);
        if (user == null) {
            return newUser(sockJSSocket);
        }
        return user;
    }

    public User newUser(SockJSSocket sockJSSocket) {
        String id = sockJSSocket.writeHandlerID();
        User user = new User(sockJSSocket);
        userMap.put(id, user);
        sockJSSocket.endHandler(event -> {
            userMap.remove(id);
        });
        return new User(sockJSSocket);
    }

}