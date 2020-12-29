package com.hjg.spring.core.conf;

import com.hjg.spring.core.model.Book;
import com.hjg.spring.core.model.Person;
import com.hjg.spring.core.model.movie.MovieRecommender;
import com.hjg.spring.core.model.movie.SimpleMovieCatalog;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @description:
 * @author: hjg
 * @createdOn: 2020/12/28
 */
@Configuration
@ComponentScan(basePackageClasses = {BookManager.class})
public class MyConf {

    @Bean
    Person person() {
        return new Person();
    }

    @Bean
    @Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    Book book() {
        return new Book();
    }

    //============测试@Qulifier========
    @Bean
    @Qualifier("main")
    public SimpleMovieCatalog simpleMovieCatalog1() {
        return new SimpleMovieCatalog("main");
    }

    @Bean
    @Qualifier("action")
    public SimpleMovieCatalog simpleMovieCatalog2() {
        return new SimpleMovieCatalog("action");
    }

    @Bean
    public MovieRecommender recommender() {
        return new MovieRecommender();
    }
}
