package com.hjg.spring.dynamic;

import com.hjg.spring.dynamic.conf.AppConfig;
import com.hjg.spring.dynamic.model.Phone;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @description:
 * @author: hjg
 * @createdOn: 2021/2/22
 */
public class DynamicRegisterApp {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        String[] array = applicationContext.getBeanNamesForType(Phone.class);
        for(String beanName : array) {
            System.out.println(beanName);
        }

        applicationContext.getBeansOfType(Phone.class).entrySet().stream().forEach(entry -> {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        });
    }
}
