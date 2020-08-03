package com.hjg.spring.aop.conf;

import com.hjg.spring.aop.aop.BookAspect;
import com.hjg.spring.aop.service.BookService;
import com.hjg.spring.aop.service.impl.BookServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AopConfiguration {

    @Bean
    BookService bookService() {
        return new BookServiceImpl();
    }

    @Bean
    BookAspect bookAspect() {
        return new BookAspect();
    }
}
