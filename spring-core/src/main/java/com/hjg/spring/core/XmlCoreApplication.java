package com.hjg.spring.core;

import com.hjg.spring.core.model.movie.MovieCatalog;
import com.hjg.spring.core.model.movie.MovieRecommender;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.stream.Stream;

/**
 * @description:
 * @author: hjg
 * @createdOn: 2020/12/29
 */
public class XmlCoreApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:application.xml");

        //获取bean name列表
        String[] names = applicationContext.getBeanNamesForType(MovieCatalog.class);
        Stream.of(names).forEach(e -> System.out.println(e));

        MovieRecommender recommender = applicationContext.getBean(MovieRecommender.class);
        recommender.printTargetCatalog();

        recommender.printAllCatalog();
    }
}
