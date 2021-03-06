package com.hjg.spring.core;

import com.hjg.spring.core.conf.formatter.MyFormatter;
import com.hjg.spring.core.javabeans.Company;
import com.hjg.spring.core.javabeans.Employee;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.ParseException;
import java.util.Locale;

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

    /**
     * 以Java进行的配置，除非是在web环境中使用web mvc，否则converter和formatter不好验证。
     */
    @Test
    public void formatterTest() throws ParseException {
        MyFormatter formatter = new MyFormatter();

        Employee jack = new Employee();
        jack.setName("Jack");
        jack.setSalary(2000f);

        Locale defaultLocale = Locale.getDefault();

        System.out.println(formatter.print(jack, defaultLocale));

        System.out.println(formatter.parse("Jim|5000", defaultLocale));
    }
}
