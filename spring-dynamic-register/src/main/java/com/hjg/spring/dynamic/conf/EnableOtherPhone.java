package com.hjg.spring.dynamic.conf;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description:
 * @author: hjg
 * @createdOn: 2021/2/22
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import(PhoneBeanDefRegistrar.class)
public @interface EnableOtherPhone {

    String[] phones();
}
