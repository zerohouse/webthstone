package org.next.ws.core.event.impl;

import io.vertx.core.http.ServerWebSocket;
import org.next.ws.core.event.standard.Communicate;
import org.next.ws.core.event.standard.GameEventType;
import org.next.ws.core.event.BroadCaster;


public class VertxBroadCaster implements BroadCaster {

    private ServerWebSocket serverWebSocket;

    public VertxBroadCaster(ServerWebSocket serverWebSocket) {
        this.serverWebSocket = serverWebSocket;
    }

    @Override
    public void broadCast(Communicate communicate) {

    }


}
