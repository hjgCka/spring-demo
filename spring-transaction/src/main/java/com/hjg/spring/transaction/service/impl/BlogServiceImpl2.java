package com.hjg.spring.transaction.service.impl;

import com.hjg.spring.transaction.mapper.BlogMapper;
import com.hjg.spring.transaction.model.Blog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @description:
 * @author: hjg
 * @createdOn: 2021/2/4
 */
@Repository
public class BlogServiceImpl2 {

    private static final Logger logger = LoggerFactory.getLogger(BlogServiceImpl2.class);

    @Autowired
    private BlogMapper blogMapper;

    /**
     * 没有实现接口，会使用CGLIB进行动态代理。
     * @param blog
     * @param newTitle
     * @return
     */
    public Blog saveAndUpdate(Blog blog, String newTitle) {
        this.blogMapper.insertBlog(blog);

        String oldTitle = blog.getTitle();
        this.blogMapper.updateBlog(blog.setTitle(newTitle), oldTitle);

        int i = 5/0;

        Blog newBlog = this.blogMapper.findByTitle(newTitle);
        return newBlog;
    }
}
