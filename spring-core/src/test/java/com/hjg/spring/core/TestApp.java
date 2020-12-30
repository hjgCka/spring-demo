package com.hjg.spring.core;

import com.hjg.spring.core.conf.BookManager;
import com.hjg.spring.core.conf.MyConf;
import com.hjg.spring.core.model.Book;
import com.hjg.spring.core.model.Person;
import com.hjg.spring.core.model.movie.MovieCatalog;
import com.hjg.spring.core.model.movie.MovieRecommender;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.stream.Stream;

/**
 * @description:
 * @author: hjg
 * @createdOn: 2020/12/29
 */
public class TestApp {

    private static final Logger logger = LoggerFactory.getLogger(TestApp.class);

    private ApplicationContext applicationContext;

    @Before
    public void init() {
        applicationContext = new AnnotationConfigApplicationContext(MyConf.class);
    }

    /**
     * 测试method-injection功能。
     */
    @Test
    public void methodInjectionTest() {
        BookManager bookManager = applicationContext.getBean(BookManager.class);

        int length = 5;
        for(int i=0; i<length; i++) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                logger.error("sleep异常", e);
            }

            Book book = bookManager.handleBookBean("Java", "James");
            logger.info("book={}", book);
        }
    }

    /**
     * 测试容器的启动和停止。
     */
    @Test
    public void startAndStopTest() {
        ConfigurableApplicationContext cAppContext = (ConfigurableApplicationContext)applicationContext;
        //这个钩子是对JVM的shutdown进行注册，来调用为bean配置的destroy方法
        cAppContext.registerShutdownHook();

        //cAppContext.close();
        cAppContext.stop();

        cAppContext.start();

        Person person = cAppContext.getBean(Person.class);
        logger.info("person = {}", person);
    }

    /**
     * 测试@Qualifier注解
     */
    @Test
    public void qualifierTest() {
        //获取bean name列表
        String[] names = applicationContext.getBeanNamesForType(MovieCatalog.class);
        Stream.of(names).forEach(e -> System.out.println(e));

        MovieRecommender recommender = applicationContext.getBean(MovieRecommender.class);
        recommender.printTargetCatalog();

        recommender.printAllCatalog();
    }

    /**
     * 测试父子容器。
     */
    @Test
    public void parentTest() {
        ApplicationContext xmlContext = new ClassPathXmlApplicationContext("classpath:application.xml");

        ConfigurableApplicationContext cAppContext = (ConfigurableApplicationContext)applicationContext;
        cAppContext.setParent(xmlContext);

        ApplicationContext parent = applicationContext.getParent();
        MovieRecommender recommender = parent.getBean(MovieRecommender.class);
        recommender.printTargetCatalog();
    }
}
