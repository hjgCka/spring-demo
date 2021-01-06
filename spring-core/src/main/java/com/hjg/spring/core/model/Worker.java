package com.hjg.spring.core.model;

import com.hjg.spring.core.conf.validator.AgeConstraint;
import lombok.Data;

/**
 * @description:
 * @author: hjg
 * @createdOn: 2021/1/6
 */
@Data
public class Worker {
    @AgeConstraint(minAge = 20)
    private Integer age;
    private String name;
}
