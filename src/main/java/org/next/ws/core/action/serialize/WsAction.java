package org.next.ws.core.action.serialize;


import org.next.ws.core.action.target.TargetOption;
import org.next.ws.core.action.target.TargetResolve;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.CONSTRUCTOR)
@Retention(RetentionPolicy.RUNTIME)
public @interface WsAction {

    String id();

    String name();

    String[] params() default {};

    TargetResolve[] target() default {};

    TargetOption targetOption() default TargetOption.NONE;

    boolean targetNeed() default false;
}
