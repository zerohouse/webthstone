package org.next.ws.web.jeo.user;

import io.vertx.ext.web.handler.sockjs.SockJSSocket;
import lombok.Getter;
import lombok.Setter;
import org.next.ws.core.game.Game;
import org.next.ws.web.matching.SocketUserPlayer;

@Getter
@Setter
public class User {
    private SockJSSocket sockJSSocket;
    private SocketUserPlayer player;
    private Game game;

    public User(SockJSSocket sockJSSocket) {
        this.sockJSSocket = sockJSSocket;
    }


    public void setPlayer(SocketUserPlayer player) {
        this.player = player;
    }

}
