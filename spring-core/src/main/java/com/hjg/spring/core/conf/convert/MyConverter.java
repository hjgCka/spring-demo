package com.hjg.spring.core.conf.convert;

import com.hjg.spring.core.javabeans.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

/**
 * @description:
 * @author: hjg
 * @createdOn: 2021/1/5
 */
public class MyConverter implements Converter<String, Employee> {

    private static final Logger logger = LoggerFactory.getLogger(MyConverter.class);

    @Override
    public Employee convert(String source) {
        logger.info("开始使用MyConverter");

        String[] array = source.split("\\|");
        Employee employee = new Employee();
        employee.setName(array[0]);
        employee.setSalary(Float.valueOf(array[1]));
        return employee;
    }
}
