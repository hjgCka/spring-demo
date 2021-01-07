package com.hjg.spring.aop.model;

import com.hjg.spring.aop.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: hjg
 * @createdOn: 2021/1/7
 */
@Configurable("phone")
@Component
public class BookAdministrator {

    @Autowired
    private BookService bookService;

    public void printBook(String name) {
        System.out.println("administrator book : " + bookService.findByName(name));
    }
}
