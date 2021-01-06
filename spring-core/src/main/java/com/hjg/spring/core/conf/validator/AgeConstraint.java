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

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default { };
}
