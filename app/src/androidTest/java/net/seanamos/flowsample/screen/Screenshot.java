package net.seanamos.flowsample.screen;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RUNTIME)
public @interface Screenshot {
    String name() default "";
}
