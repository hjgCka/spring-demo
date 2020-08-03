package com.hjg.spring.aop.aop;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CacheUpdate {

    String name() default "";
    String author() default "";
}
