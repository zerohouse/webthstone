package org.next.ws.web.matching;

import io.vertx.ext.web.handler.sockjs.SockJSSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

@Service
public class MatchingService {

    private static final Logger logger = LoggerFactory.getLogger(MatchingService.class);
    private final Queue<SockJSSocket> waitingQue;

    public MatchingService() {
        this.waitingQue = new ConcurrentLinkedDeque<>();
    }


    public void enqueue(SockJSSocket sockJSSocket) {
        waitingQue.add(sockJSSocket);
    }


    @Scheduled(fixedDelay = 5000)
    private void makeMatch() {
        logger.debug("매칭을 시작합니다. {}", waitingQue);
        while (waitingQue.size() >= 2) {
            SockJSSocket sockJSSocket = connectedSocket(waitingQue);
            SockJSSocket sockJSSocket2 = connectedSocket(waitingQue);
            if (sockJSSocket == null || sockJSSocket2 == null)
                return;
            makeMatch(sockJSSocket, sockJSSocket2);
        }
    }

    private void makeMatch(SockJSSocket sockJSSocket, SockJSSocket sockJSSocket2) {
        Match match = new Match(sockJSSocket, sockJSSocket2);
        match.start();
    }

    private SockJSSocket connectedSocket(Queue<SockJSSocket> waitingQue) {
        /*
        * [TODO] 여기 커넥트 된 소켓인지 확인하는 로직 추가
        * */
        return waitingQue.poll();
    }

}
