package com.hjg.spring.core.conf.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @description:
 * @author: hjg
 * @createdOn: 2021/1/6
 */
public class MyAgeValidator implements ConstraintValidator<AgeConstraint, Integer> {

    AgeConstraint constraintAnnotation;

    @Override
    public void initialize(AgeConstraint constraintAnnotation) {
        this.constraintAnnotation = constraintAnnotation;
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        int minAge = constraintAnnotation.minAge();
        int maxAge = constraintAnnotation.maxAge();

        if(value > maxAge || value < minAge) {
            return false;
        }
        return true;
    }
}
