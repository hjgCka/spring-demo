package com.hjg.spring.aop.service;

import com.hjg.spring.aop.model.Book;

public interface BookService {

    Book findByName(String name);

    void updateBook(Book book);
}
