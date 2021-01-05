package com.hjg.spring.core.conf.formatter;

import com.hjg.spring.core.javabeans.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Formatter;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @description:
 * @author: hjg
 * @createdOn: 2021/1/5
 */
public class EmployeeAnnotationFormatterFactory implements AnnotationFormatterFactory<EmployeeFormatter> {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeAnnotationFormatterFactory.class);

    /**
     * 注解将要作用的类型。
     * @return
     */
    @Override
    public Set<Class<?>> getFieldTypes() {
        return new HashSet<Class<?>>(
                Arrays.asList(new Class<?>[]{
                        Employee.class
                })
        );
    }

    @Override
    public Printer<?> getPrinter(EmployeeFormatter annotation, Class<?> fieldType) {
        //打印注解了的字段的值
        return this.configureFormatter(annotation, fieldType);
    }

    @Override
    public Parser<?> getParser(EmployeeFormatter annotation, Class<?> fieldType) {
        //解析从客户端发来的数据
        return this.configureFormatter(annotation, fieldType);
    }

    private Formatter<Employee> configureFormatter(EmployeeFormatter annotation, Class<?> fieldType) {
        String value = annotation.value();
        logger.info("value = {}", value);
        return new MyFormatter();
    }
}
