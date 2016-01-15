package org.next.ws.web.game;

import io.vertx.ext.web.handler.sockjs.SockJSSocket;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class GameService {

    private ConcurrentHashMap<String, Match> matches = new ConcurrentHashMap<>();

    public void makeMatch(SockJSSocket sockJSSocket, SockJSSocket sockJSSocket2) {
        Match match = new Match(sockJSSocket, sockJSSocket2);
        match.start();
    }
}
