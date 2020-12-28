package com.hjg.spring.core.model;

import lombok.Data;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @description:
 * @author: hjg
 * @createdOn: 2020/12/28
 */
@Data
public class Book {
    private String name;
    private String author;

    private String date;

    @PostConstruct
    public void postConstruct() {
        System.out.println("postConstruct " + this.toString());
    }

    @PreDestroy
    public void preDestruction() {
        System.out.println("preDestruction " + this.toString());
    }
}
