package com.hjg.spring.aop.service;

import com.hjg.spring.aop.model.Book;

public interface BookService {

    Book findByName(String name);

    void updateBookAll(Book book, String name, String author);

    void updateBookName(Book book, String name);

    void updateBookAuthor(Book book, String author);
}
