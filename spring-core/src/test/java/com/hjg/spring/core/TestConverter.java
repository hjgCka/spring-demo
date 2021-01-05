package com.hjg.spring.core;

import com.hjg.spring.core.javabeans.Company;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @description:
 * @author: hjg
 * @createdOn: 2021/1/5
 */
public class TestConverter {

    @Test
    public void converterTest() {
        String path = "classpath:application.xml";
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(path);

        Company company = applicationContext.getBean(Company.class);
        System.out.println(company);
    }
}
