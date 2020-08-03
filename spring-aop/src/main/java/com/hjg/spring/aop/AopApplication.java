package com.hjg.spring.aop;

import com.hjg.spring.aop.conf.AopConfiguration;
import com.hjg.spring.aop.model.Book;
import com.hjg.spring.aop.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AopApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(AopConfiguration.class);

        BookService bookService = applicationContext.getBean(BookService.class);

        String name = "Core Java";
        Book book = bookService.findByName(name);
        System.out.println("book = " + book);

        bookService.updateBook(book);
        System.out.println("after update, book = " + book);
    }
}
