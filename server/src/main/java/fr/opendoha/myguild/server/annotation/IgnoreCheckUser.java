package fr.opendoha.myguild.server.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;

@Target(METHOD)
@Retention(RUNTIME)
@Documented
public @interface IgnoreCheckUser {
    
}
