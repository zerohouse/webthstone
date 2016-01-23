package org.next.ws.web.user;

import io.vertx.ext.web.handler.sockjs.SockJSSocket;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.next.ws.core.game.Game;
import org.next.ws.jeo.Jeo;
import org.next.ws.web.matching.SocketUserPlayer;

@Getter
@Setter
@ToString(exclude = {"sockJSSocket", "player"})
public class User {
    private SockJSSocket sockJSSocket;
    private SocketUserPlayer player;
    private Game game;
    private String name;
    private String img;
    private String id;

    public User(SockJSSocket sockJSSocket) {
        this.sockJSSocket = sockJSSocket;
    }

    public void setPlayer(SocketUserPlayer player) {
        this.player = player;
    }

    public void sendMessage(String message) {
        Jeo.event(sockJSSocket, Jeo.MESSAGE, message);

    }

}
