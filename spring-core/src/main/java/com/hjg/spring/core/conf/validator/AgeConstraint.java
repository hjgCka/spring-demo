package com.hjg.spring.core.conf.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description:
 * @author: hjg
 * @createdOn: 2021/1/6
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MyAgeValidator.class)
@NotNull
public @interface AgeConstraint {
    int minAge() default 0;
    int maxAge() default 65;

    String message() default "验证失败，age不合格";

    /**
     * 同一个validator，用在不同的情形下用法不同，需要进行分组。
     * 数组中的Class不需要任何实现，提供空Class即可。
     * @return
     */
    Class<?>[] groups() default {};

    /**
     * 这个属性用于携带元数据信息，自定义注解需要带上，不然报错
     * @return
     */
    Class<? extends Payload>[] payload() default { };
}
