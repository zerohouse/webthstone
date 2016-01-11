package org.next.ws.gamelauncher;

import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.ServerWebSocket;
import org.next.ws.core.event.EventRunner;


public class GameHandler implements Handler<ServerWebSocket> {
    @Override
    public void handle(ServerWebSocket serverWebSocket) {
        serverWebSocket.handler(new Handler<Buffer>() {
            EventRunner eventRunner = new EventRunner();
            @Override
            public void handle(Buffer event) {
                eventRunner.run(event);
            }
        });
    }
}
