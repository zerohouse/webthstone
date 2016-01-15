package org.next.ws.web.jeo;

import io.vertx.ext.web.handler.sockjs.SockJSSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class WebSocketRepository {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketRepository.class);

    private final ConcurrentHashMap<String, SockJSSocket> sockMap;

    public WebSocketRepository() {
        sockMap = new ConcurrentHashMap<>();
    }


    public void connect(SockJSSocket sockJSSocket) {

    }

    public void disconnect(SockJSSocket sockJSSocket) {

    }
}
