package org.next.ws.launcher;


import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.ServerWebSocket;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.handler.sockjs.BridgeOptions;
import io.vertx.ext.web.handler.sockjs.PermittedOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;
import org.next.ws.core.event.EventRunner;
import org.springframework.stereotype.Component;

@Component
public class WebSocketServer extends AbstractVerticle {


    @Override
    public void start() throws Exception {

        Router router = Router.router(vertx);

        BridgeOptions bridgeOptions = new BridgeOptions()
                .addInboundPermitted(new PermittedOptions().setAddress("chat.to.server"))
                .addOutboundPermitted(new PermittedOptions().setAddress("chat.to.client"));
        EventBus eventBus = vertx.eventBus();

        SockJSHandler sockJSHandler = SockJSHandler.create(vertx).bridge(bridgeOptions);
        router.route("/eventbus/*").handler(sockJSHandler);

        router.route().handler(StaticHandler.create());

        vertx.createHttpServer().requestHandler(router::accept).listen(8081);

        eventBus.consumer("chat.to.server").handler(message -> {
            eventBus.publish("chat.to.client", message.body());
        });
    }
}
