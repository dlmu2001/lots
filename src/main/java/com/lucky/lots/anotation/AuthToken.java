package com.lucky.lots.anotation;

import java.lang.annotation.*;
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthToken {
}
