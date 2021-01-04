package com.hjg.spring.core.javabeans;

import lombok.Data;

/**
 * @description:
 * @author: hjg
 * @createdOn: 2020/12/31
 */
@Data
public class Company {
    private String name;
    private Employee managingDirector;
}
