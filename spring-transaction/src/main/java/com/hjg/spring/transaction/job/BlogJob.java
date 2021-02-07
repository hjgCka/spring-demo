package com.hjg.spring.transaction.job;

import com.hjg.spring.transaction.model.Blog;

/**
 * @description:
 * @author: hjg
 * @createdOn: 2021/2/7
 */
public interface BlogJob {

    Blog findById(String id);
}
