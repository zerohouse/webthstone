package org.next.ws.web.jeo;

import org.springframework.stereotype.Component;

import javax.persistence.SqlResultSetMapping;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface JeoEvent {
    String value();

    String eventDest() default "";

}
