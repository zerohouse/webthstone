package org.next.ws.web.game;

import org.next.ws.web.jeo.JeoController;
import org.next.ws.web.jeo.JeoEvent;
import org.next.ws.web.jeo.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@JeoController
public class GameController {

    @JeoEvent("card.submit")
    public void gamePlay(User user, Integer id) {
        user.getPlayer().useCard(id);
    }

}
