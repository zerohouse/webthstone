package org.next.ws.web.matching;

import org.next.ws.web.jeo.JeoController;
import org.next.ws.web.jeo.JeoEvent;
import org.next.ws.web.jeo.user.User;
import org.springframework.beans.factory.annotation.Autowired;

@JeoController
public class MatchingController {

    @Autowired
    MatchingService matchingService;

    @JeoEvent(value = "game.play", eventDest = "message")
    public String gamePlay(User user){
        matchingService.enqueue(user);
        return "같이 게임할 플레이어를 찾는 중";
    }
}
