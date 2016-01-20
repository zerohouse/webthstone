package org.next.ws.web.matching;

import org.next.ws.core.game.Game;
import org.next.ws.core.game.player.deck.Deck;
import org.next.ws.core.hero.healer.Healer;
import org.next.ws.web.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

@Service
public class MatchingService {

    private static final Logger logger = LoggerFactory.getLogger(MatchingService.class);

    private final Queue<User> waitingQue;

    public MatchingService() {
        this.waitingQue = new ConcurrentLinkedDeque<>();
    }


    public String enqueue(User user) {
        if (user.getGame() != null)
            return "게임에 이미 참여하고 있습니다.";
        if (waitingQue.contains(user))
            return "이미 대기 중입니다.";
        waitingQue.add(user);
        user.getSockJSSocket().endHandler(event -> {
            waitingQue.remove(user);
        });
        return "같이 게임할 사람을 기다립니다.";
    }

    @Scheduled(fixedDelay = 5000)
    private void makeGameAndStart() {
        logger.debug("매칭을 시작합니다. {}", waitingQue);
        while (waitingQue.size() >= 2) {
            User user = waitingQue.poll();
            User user2 = waitingQue.poll();
            makeGameAndStart(user, user2);
        }
    }

    private void makeGameAndStart(User user, User user2) {
        Game game = new Game();
        try {
            SocketUserPlayer player1 = new SocketUserPlayer(user, new Healer("사제", "/resource/icon/coin.img", game), new Deck("[1,2,3,4,5,1,2,3,4,5,1,2,3]"));
            SocketUserPlayer player2 = new SocketUserPlayer(user2, new Healer("사제", "/resource/icon/coin.img", game), new Deck("[1,2,3,4,5,1,2,3,4,5,1,2,3]"));

            user.setPlayer(player1);
            user.setGame(game);
            user2.setPlayer(player2);
            user2.setGame(game);

            game.setPlayer(player1, player2);
            game.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
