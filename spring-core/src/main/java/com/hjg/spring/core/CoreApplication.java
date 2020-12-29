package com.hjg.spring.core;

import com.hjg.spring.core.conf.MyConf;
import com.hjg.spring.core.model.Person;
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
        Person person = applicationContext.getBean(Person.class);
        logger.info("person = {}", person);
    }
}
