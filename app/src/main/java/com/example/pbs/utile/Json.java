package com.example.pbs.utile;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/** Customizes how a field is encoded as JSON. */
@Target({FIELD, METHOD})
@Retention(RUNTIME)
@Documented
public  @interface Json {
    String name();
}
