package com.hjg.spring.transaction;

import com.hjg.spring.transaction.job.BlogJob;
import com.hjg.spring.transaction.mapper.BlogMapper;
import com.hjg.spring.transaction.model.Blog;
import com.hjg.spring.transaction.service.BlogService;
import com.hjg.spring.transaction.service.impl.BlogServiceImpl2;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
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

    @Test
    public void trasactionTest2() {
        BlogServiceImpl2 blogServiceImpl2 = applicationContext.getBean(BlogServiceImpl2.class);

        String newTitle = "Thinking in Java";
        Blog blog = blogServiceImpl2.saveAndUpdate(new Blog()
                        .setTitle("Thinking in C++")
                        .setCreateTime(new Date()),
                newTitle);

        System.out.println(blog);
    }

    /**
     * BlogServiceImpl2返回BlogServiceImpl2$$EnhancerBySpringCGLIB$$7e05728。
     * 说明返回的是一个CGLIB的代理类。
     *
     * BlogService返回com.sun.proxy.$Proxy20，是一个JDK的代理类。
     */
    @Test
    public void serviceClassTest() {
        BlogServiceImpl2 blogServiceImpl2 = applicationContext.getBean(BlogServiceImpl2.class);
        System.out.println(blogServiceImpl2.getClass().getName());

        BlogService blogService = applicationContext.getBean(BlogService.class);
        System.out.println(blogService.getClass().getName());
    }

    @Test
    public void transactionalTest() throws NoSuchMethodException {
        BlogJob blogJob = applicationContext.getBean(BlogJob.class);

        Method findMethod = BlogJob.class.getMethod("findById", String.class);
        System.out.println(findMethod.getName());

        //CGLIB产生的代理类，并没有获取方法上的注解信息。
        Annotation[] annotations = findMethod.getAnnotations();
        Arrays.stream(annotations).forEach(e -> System.out.println(e));
    }
}
