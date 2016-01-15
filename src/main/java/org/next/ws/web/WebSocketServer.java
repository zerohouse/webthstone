package org.next.ws.web;


import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;
import org.next.ws.web.jeo.WebSocketHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WebSocketServer extends AbstractVerticle {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketServer.class);

    @Autowired
    WebSocketHandler webSocketHandlingService;

    @Override
    public void start() throws Exception {
        Router router = Router.router(vertx);
        SockJSHandler sockJSHandler = SockJSHandler.create(vertx);
        sockJSHandler.socketHandler(webSocketHandlingService);
        router.route("/socket/*").handler(sockJSHandler);
        vertx.createHttpServer().requestHandler(router::accept).listen(8080);
    }

}
