package org.next.ws.web.game;

import io.vertx.ext.web.handler.sockjs.SockJSSocket;
import org.next.ws.web.user.User;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class GameService {

    private ConcurrentHashMap<String, Match> matches = new ConcurrentHashMap<>();

    public void makeMatch(User user, User user2) {
        Match match = new Match(user, user2);
        match.start();
    }
}
