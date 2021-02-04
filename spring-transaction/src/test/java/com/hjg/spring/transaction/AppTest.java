package com.hjg.spring.transaction;

import com.hjg.spring.transaction.mapper.BlogMapper;
import com.hjg.spring.transaction.model.Blog;
import com.hjg.spring.transaction.service.BlogService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: hjg
 * @createdOn: 2021/2/4
 */
public class AppTest {

    ApplicationContext applicationContext;

    @Before
    public void init() {
        applicationContext = new ClassPathXmlApplicationContext("classpath:application.xml");
    }

    @Test
    public void insertListTest() {
        BlogMapper blogMapper = applicationContext.getBean(BlogMapper.class);

        List<Blog> list = new ArrayList<>();
        list.add(new Blog().setTitle("Information technology").setCreateTime(new Date()));
        list.add(new Blog().setTitle("Math").setCreateTime(new Date()));

        //插入之后，workbench无法查询到，重新打开可以查询到
        blogMapper.insertBlogList(list);

        List<Blog> blogs = blogMapper.queryBlogs();
        blogs.stream().forEach(e -> System.out.println(e));
    }

    @Test
    public void trasactionTest1() {
        BlogService blogService = applicationContext.getBean(BlogService.class);

        String newTitle = "Thinking in Java";
        Blog blog = blogService.saveAndUpdate(new Blog()
                        .setTitle("Thinking in C++")
                        .setCreateTime(new Date()),
                newTitle);

        System.out.println(blog);
    }
}
