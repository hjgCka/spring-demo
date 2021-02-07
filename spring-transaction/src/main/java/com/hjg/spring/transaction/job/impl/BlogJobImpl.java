package com.hjg.spring.transaction.job.impl;

import com.hjg.spring.transaction.job.BlogJob;
import com.hjg.spring.transaction.mapper.BlogMapper;
import com.hjg.spring.transaction.model.Blog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description:
 * @author: hjg
 * @createdOn: 2021/2/7
 */
@Repository
public class BlogJobImpl implements BlogJob {

    private static final Logger logger = LoggerFactory.getLogger(BlogJobImpl.class);

    @Autowired
    private BlogMapper blogMapper;
    
    @Override
    @Transactional
    public Blog findById(String id) {
        Blog blog = this.blogMapper.findById(id);
        logger.info("blog={}", blog);
        return blog;
    }
}
