package com.hjg.spring.aop.conf;

import com.hjg.spring.aop.service.BookService;
import com.hjg.spring.aop.service.impl.BookServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AopConfiguration {

    @Bean
    public BookService bookService() {
        return new BookServiceImpl();
    }
}
