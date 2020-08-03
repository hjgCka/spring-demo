package com.hjg.spring.aop.service.impl;

import com.hjg.spring.aop.aop.CacheUpdate;
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
    @CacheUpdate(name = "#p1", author = "#p2")
    public void updateBookAll(Book book, String name, String author) {
        book.setName(name);
        book.setAuthor(author);
    }

    @Override
    @CacheUpdate(name = "#p1")
    public void updateBookName(Book book, String name) {
        book.setName(name);
    }

    @Override
    @CacheUpdate(author = "#p1")
    public void updateBookAuthor(Book book, String author) {
        book.setAuthor(author);
    }


}
