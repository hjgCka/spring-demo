package com.hjg.spring.core.conf;

import com.hjg.spring.core.conf.convert.MyConverter;
import com.hjg.spring.core.conf.core.BookManager;
import com.hjg.spring.core.conf.formatter.EmployeeAnnotationFormatterFactory;
import com.hjg.spring.core.model.Book;
import com.hjg.spring.core.model.Person;
import com.hjg.spring.core.model.Worker;
import com.hjg.spring.core.model.movie.MovieRecommender;
import com.hjg.spring.core.model.movie.SimpleMovieCatalog;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.datetime.DateTimeFormatAnnotationFormatterFactory;
import org.springframework.format.number.NumberFormatAnnotationFormatterFactory;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * @description:
 * @author: hjg
 * @createdOn: 2020/12/28
 */
@Configuration
@ComponentScan(basePackageClasses = {BookManager.class})
@PropertySource({"classpath:/com/hjg/data/jdbc.properties"})
public class MyConf {

    @Bean
    LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    Worker worker() {
        return new Worker();
    }

    @Bean
    Person person() {
        Person person = new Person();
        person.setAge(30);
        person.setName("Jack");
        return person;
    }

    /**
     * 用于方法注入，查看BookManager
     * @return
     */
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

    //集中配置formatter和converter，bean的id为conversionService，spring会使用这个bean获取converter
    @Bean
    public FormattingConversionService conversionService() {
        // Use the DefaultFormattingConversionService but do not register defaults
        DefaultFormattingConversionService conversionService = new
                DefaultFormattingConversionService(false);

        // Ensure @NumberFormat @DateFormat is still supported
        conversionService.addFormatterForFieldAnnotation(new NumberFormatAnnotationFormatterFactory());
        conversionService.addFormatterForFieldAnnotation(new DateTimeFormatAnnotationFormatterFactory());
        conversionService.addFormatterForFieldAnnotation(new EmployeeAnnotationFormatterFactory());

        //注册converter，与xml配置不同的是，converter使用场景是web
        conversionService.addConverter(new MyConverter());

        // Register date conversion with a specific global format
        DateFormatterRegistrar registrar = new DateFormatterRegistrar();
        registrar.setFormatter(new DateFormatter("yyyyMMdd"));
        registrar.registerFormatters(conversionService);

        return conversionService;
    }
}
