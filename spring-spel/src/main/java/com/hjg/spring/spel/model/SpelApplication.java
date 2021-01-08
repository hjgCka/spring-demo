package com.hjg.spring.spel.model;

import com.hjg.spring.spel.model.conf.SpelConfig;
import com.hjg.spring.spel.model.model.Inventor;
import com.hjg.spring.spel.model.model.Society;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @description:
 * @author: hjg
 * @createdOn: 2021/1/8
 */
public class SpelApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpelConfig.class);
        Society society = applicationContext.getBean(Society.class);

        Inventor inventor = applicationContext.getBean(Inventor.class);

        System.out.println(inventor);
        System.out.println(society);
    }
}
