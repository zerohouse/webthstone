package org.next.ws.web.jeo;

import io.vertx.core.Handler;
import io.vertx.ext.web.handler.sockjs.SockJSSocket;
import org.next.ws.web.jeo.user.User;
import org.next.ws.web.jeo.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WebSocketHandler implements Handler<SockJSSocket> {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketHandler.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    JeoEventResolver jeoEventResolver;

    @Override
    public void handle(SockJSSocket sockJSSocket) {
        logger.debug("socket client {} connected", sockJSSocket.writeHandlerID());

        User user = userRepository.newUser(sockJSSocket);

        sockJSSocket.handler(buffer -> {
            jeoEventResolver.execute(buffer.toString(), user);
        });
    }

}
