package org.next.ws.web;


import io.vertx.core.Vertx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;

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

