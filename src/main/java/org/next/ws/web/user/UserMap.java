package org.next.ws.web.user;

import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.handler.sockjs.SockJSSocket;
import org.next.ws.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class UserMap {

    private static final Logger logger = LoggerFactory.getLogger(UserMap.class);
    ConcurrentHashMap<String, User> userMap = new ConcurrentHashMap<>();

    @Autowired
    UserRepository userRepository;

    public User getUser(SockJSSocket sockJSSocket) {
        String id = sockJSSocket.writeHandlerID();
        logger.debug("socket fbId : {}", id);
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
        return user;
    }

    public User update(User user, String id, String name, String img) {
        User db = userRepository.findByFbId(id);
        if (db != null) {
            db.setPlayer(user.getPlayer());
            db.setSockJSSocket(user.getSockJSSocket());
            user = db;
        } else {
            userRepository.save(user);
        }
        if (id != null)
            user.setFbId(id);
        if (name != null)
            user.setName(name);
        if (img != null)
            user.setImg(img);
        userMap.put(user.getSockJSSocket().writeHandlerID(), user);
        return user;
    }
}
