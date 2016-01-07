package org.next.ws;


import io.vertx.core.Vertx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class ApplicationLauncher {

    @Autowired
    private StaticServer staticServer;


    public static void main(String[] args) {

        // This is basically the same example as the web-examples staticsite example but it's booted using
        // SpringBoot, not Vert.x
        SpringApplication.run(ApplicationLauncher.class, args);
    }

    @PostConstruct
    public void deployVerticle() {
        Vertx.vertx().deployVerticle(staticServer);
    }

}

