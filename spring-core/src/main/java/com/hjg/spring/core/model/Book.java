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
        String threadName = Thread.currentThread().getName();
        System.out.println("threadName = " + threadName + ", postConstruct " + this.toString());
    }

    /**
     * 由于原型的bean，容器不会进行销毁的回调。
     */
    @PreDestroy
    public void preDestruction() {
        String threadName = Thread.currentThread().getName();
        System.out.println("threadName = " + threadName + ", preDestruction " + this.toString());
    }
}
