package com.hjg.spring.core.conf.formatter;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @description:
 * @author: hjg
 * @createdOn: 2021/1/5
 */
@Documented
@Retention(value=RUNTIME)
@Target(value={METHOD,FIELD,PARAMETER,ANNOTATION_TYPE})
public @interface EmployeeFormatter {

    String value() default "";
}
