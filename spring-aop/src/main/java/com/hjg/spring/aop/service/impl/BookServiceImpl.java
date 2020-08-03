package com.hjg.spring.aop.service.impl;

import com.hjg.spring.aop.model.Book;
import com.hjg.spring.aop.service.BookService;

import java.time.LocalDate;

public class BookServiceImpl implements BookService {

    @Override
    public Book findByName(String name) {
        Book book = new Book();
        book.setAuthor("James");
        book.setName("Core Java");
        book.setPrice(50.00);
        book.setPublishDate(LocalDate.now());
        return book;
    }

    @Override
    public void updateBook(Book book) {
        book.setPrice(70.00);
    }
}
