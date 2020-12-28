package com.hjg.spring.core;

import com.hjg.spring.core.conf.BookManager;
import com.hjg.spring.core.conf.MyConf;
import com.hjg.spring.core.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @description:
 * @author: hjg
 * @createdOn: 2020/12/28
 */
public class CoreApplication {

    private static final Logger logger = LoggerFactory.getLogger(CoreApplication.class);

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyConf.class);
        BookManager bookManager = applicationContext.getBean(BookManager.class);

        for(int i=0; i<5; i++) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                logger.error("sleep异常", e);
            }

            Book book = bookManager.setBook("Java", "James");
            logger.info("book={}", book);
        }

    }
}
