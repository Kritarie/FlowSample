package net.seanamos.flowsample.screen;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.lang.annotation.ElementType.METHOD;

@Documented
@Retention(RUNTIME)
@Target(METHOD)
public @interface WithHistory {
    String value();
}
