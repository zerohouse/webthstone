package org.next.ws.web.jeo;

import io.vertx.core.Handler;
import io.vertx.ext.web.handler.sockjs.SockJSSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WebSocketHandler implements Handler<SockJSSocket> {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketHandler.class);

    @Autowired
    WebSocketRepository webSocketRepository;

    @Autowired
    JeoEventResolver jeoEventResolver;

    @Override
    public void handle(SockJSSocket sockJSSocket) {
        logger.debug("socket client {} connected", sockJSSocket.writeHandlerID());
        webSocketRepository.connect(sockJSSocket);

        sockJSSocket.handler(buffer -> {
            jeoEventResolver.execute(buffer.toString(), sockJSSocket);
        });

        sockJSSocket.endHandler(disconnect -> {
            webSocketRepository.disconnect(sockJSSocket);
        });
    }

}
