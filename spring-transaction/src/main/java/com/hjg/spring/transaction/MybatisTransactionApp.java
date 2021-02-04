package com.hjg.spring.transaction;

import com.hjg.spring.transaction.model.Blog;
import com.hjg.spring.transaction.service.BlogService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @description:
 * @author: hjg
 * @createdOn: 2021/2/4
 */
public class MybatisTransactionApp {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:application.xml");

        BlogService blogService = applicationContext.getBean(BlogService.class);

        String id = "1";
        Blog blog = blogService.getBlogById(id);
        System.out.println(blog);
    }
}
