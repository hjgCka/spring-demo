package com.hjg.spring.core.conf.core;

import com.hjg.spring.core.model.Book;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 使用method-injection，每次都创建新的实例。
 * 使用原型模式以及方法注入时，不要和代理结合使用，spring-aop只应用于container管理的bean
 * 严格来说，原型模式并不是spring容器进行管理。
 *
 * 对于request级别或session作用域的bean，可以使用aop进行代理。
 *
 * @description:
 * @author: hjg
 * @createdOn: 2020/12/28
 */
@Component
public abstract class BookManager {

    DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");

    public Book handleBookBean(String name, String author) {
        Book book = this.createBook();

        book.setName(name);

        book.setAuthor(author);

        LocalDateTime now = LocalDateTime.now();
        String date = now.format(df);
        book.setDate(date);

        return book;
    }

    @Lookup
    protected abstract Book createBook();
}
