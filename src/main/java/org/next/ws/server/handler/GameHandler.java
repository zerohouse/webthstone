package org.next.ws.server.handler;

import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import org.next.ws.server.Handle;
import org.next.ws.server.SocketHandler;

@SocketHandler
public class GameHandler {

    @Handle("chat.to.server")
    public void handle(EventBus eventBus, Message message) {
        eventBus.publish("chat.to.client", "fromServer" + message.body());
    }


}
