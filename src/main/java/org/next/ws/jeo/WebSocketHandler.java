package org.next.ws.jeo;

import io.vertx.core.Handler;
import io.vertx.ext.web.handler.sockjs.SockJSSocket;
import org.next.ws.web.user.UserMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class WebSocketHandler implements Handler<SockJSSocket> {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketHandler.class);

    @Autowired
    UserMap userRepository;

    @Autowired
    JeoEventResolver jeoEventResolver;

    @Autowired
    ApplicationContext applicationContext;

    @Override
    public void handle(SockJSSocket sockJSSocket) {
        logger.debug("socket client {} connected", sockJSSocket.writeHandlerID());
        sockJSSocket.handler(buffer -> {
            try {
                jeoEventResolver.execute(buffer.toString(),  sockJSSocket);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}
