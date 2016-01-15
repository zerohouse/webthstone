package org.next.ws.web.user;

import io.vertx.ext.web.handler.sockjs.SockJSSocket;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private SockJSSocket jsSocket;
}
