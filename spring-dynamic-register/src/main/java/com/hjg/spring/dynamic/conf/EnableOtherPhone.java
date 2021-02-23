package com.hjg.spring.dynamic.conf;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @description:
 * @author: hjg
 * @createdOn: 2021/2/22
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import(PhoneBeanDefRegistrar.class)
public @interface EnableOtherPhone {

    String[] phones();
}
