package com.hjg.spring.core;

import com.hjg.spring.core.conf.MyConf;
import com.hjg.spring.core.model.Person;
import com.hjg.spring.core.model.Worker;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @description:
 * @author: hjg
 * @createdOn: 2021/1/6
 */
public class TestValidationApp {

    private ApplicationContext applicationContext;
    private Validator validator;

    @Before
    public void init() {
        applicationContext = new AnnotationConfigApplicationContext(MyConf.class);
        validator = applicationContext.getBean(Validator.class);
    }

    @Test
    public void validationTest() {
        Person person = applicationContext.getBean(Person.class);
        person.setAge(-5);

        Set<ConstraintViolation<Person>> result = validator.validate(person);

        printValidationResult(result);
    }

    @Test
    public void validationAgeTest() {
        Worker worker = applicationContext.getBean(Worker.class);
        worker.setAge(10);

        Set<ConstraintViolation<Worker>> result = validator.validate(worker);
        printValidationResult(result);
    }

    private <T> void printValidationResult(Set<ConstraintViolation<T>> result) {
        if(result.size() > 0) {
            result.stream().forEach(item -> {
                String propertyPath = item.getPropertyPath().toString();
                String message = item.getMessage();
                Object invalidValue = item.getInvalidValue();
                System.out.println("propertyPath = " + propertyPath + ", message = " + message + ", invalidValue = " + invalidValue);
            });
        } else {
            System.out.println("Validation Successfully");
        }
    }
}
