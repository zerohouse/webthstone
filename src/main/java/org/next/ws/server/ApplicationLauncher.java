package org.next.ws.server;


import io.vertx.core.Vertx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class ApplicationLauncher {

    @Autowired
    private WebSocketServer websocketServer;

    public static void main(String[] args) {
        SpringApplication.run(ApplicationLauncher.class, args);
    }

    @PostConstruct
    public void deployVerticle() {
        Vertx.vertx().deployVerticle(websocketServer);
    }

}

