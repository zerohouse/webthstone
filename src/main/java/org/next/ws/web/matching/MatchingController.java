package org.next.ws.web.matching;

import io.vertx.ext.web.handler.sockjs.SockJSSocket;
import org.next.ws.web.jeo.JeoController;
import org.next.ws.web.jeo.JeoEvent;
import org.springframework.beans.factory.annotation.Autowired;

@JeoController
public class MatchingController {

    @Autowired
    MatchingService matchingService;

    @JeoEvent(value = "game.play", eventDest = "message")
    public String gamePlay(SockJSSocket sockJSSocket){
        matchingService.enqueue(sockJSSocket);
        return "같이 게임할 플레이어를 찾는 중";
    }
}
