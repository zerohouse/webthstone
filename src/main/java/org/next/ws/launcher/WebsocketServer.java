package org.next.ws.launcher;


import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerRequest;
import org.next.ws.gamelauncher.GameHandler;
import org.springframework.stereotype.Component;

@Component
public class WebSocketServer extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        vertx.createHttpServer().websocketHandler(new GameHandler()).requestHandler(request -> {
        }).listen(9000);
    }
}
