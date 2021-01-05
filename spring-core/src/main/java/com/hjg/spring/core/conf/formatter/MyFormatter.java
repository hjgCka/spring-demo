package com.hjg.spring.core.conf.formatter;

import com.hjg.spring.core.javabeans.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

/**
 * @description:
 * @author: hjg
 * @createdOn: 2021/1/5
 */
public class MyFormatter implements Formatter<Employee> {

    private static final Logger logger = LoggerFactory.getLogger(MyFormatter.class);

    @Override
    public Employee parse(String text, Locale locale) throws ParseException {
        String country = locale.getCountry();
        String language = locale.getLanguage();
        logger.info("country = " + country + ", language = " + language);

        String[] array = text.split("\\|");
        Employee employee = new Employee();
        employee.setName(array[0]);
        employee.setSalary(Float.valueOf(array[1]));
        return employee;
    }

    @Override
    public String print(Employee object, Locale locale) {
        String name = object.getName();
        String salary = object.getSalary() + "";

        return name + "|" + salary;
    }
}
