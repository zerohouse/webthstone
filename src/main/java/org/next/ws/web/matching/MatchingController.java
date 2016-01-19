package org.next.ws.web.matching;

import org.next.ws.web.jeo.JeoController;
import org.next.ws.web.jeo.JeoEvent;
import org.next.ws.web.user.User;
import org.springframework.beans.factory.annotation.Autowired;

@JeoController
public class MatchingController {

    @Autowired
    MatchingService matchingService;

    @JeoEvent(value = "game.play", eventDest = "message")
    public String gamePlay(User user) {
        return matchingService.enqueue(user);
    }
}
