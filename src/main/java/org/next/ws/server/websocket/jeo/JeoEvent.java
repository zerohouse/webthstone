package org.next.ws.server.websocket.jeo;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface JeoEvent {
    String value();

    String eventDest() default "";

}
