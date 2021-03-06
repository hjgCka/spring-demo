package com.hjg.spring.aop;

import com.hjg.spring.aop.conf.AopConfiguration;
import com.hjg.spring.aop.model.Book;
import com.hjg.spring.aop.model.BookAdministrator;
import com.hjg.spring.aop.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AopApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(AopConfiguration.class);

        String name = "Core Java";

        BookAdministrator bookAdministrator = applicationContext.getBean(BookAdministrator.class);
        bookAdministrator.printBook(name);

        BookService bookService = applicationContext.getBean(BookService.class);
        Book book = bookService.findByName(name);
        System.out.println("book = " + book);

        String newAuthor = "Jackson";
        String newName = "Thinking in Java";
        bookService.updateBookAll(book, newName, newAuthor);
        System.out.println("after update, book = " + book);
    }
}
