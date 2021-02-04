package com.hjg.spring.transaction.service.impl;

import com.hjg.spring.transaction.mapper.BlogMapper;
import com.hjg.spring.transaction.model.Blog;
import com.hjg.spring.transaction.service.BlogService;
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
public class BlogServiceImpl implements BlogService {

    private static final Logger logger = LoggerFactory.getLogger(BlogServiceImpl.class);

    @Autowired
    private BlogMapper blogMapper;

    @Override
    public Blog getBlogById(String id) {
        Blog blog = blogMapper.findById(id);
        logger.info("blog = {}", blog);
        return blog;
    }
}
