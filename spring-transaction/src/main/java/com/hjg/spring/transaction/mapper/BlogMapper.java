package com.hjg.spring.transaction.mapper;

import com.hjg.spring.transaction.model.Blog;

/**
 * @description:
 * @author: hjg
 * @createdOn: 2021/2/4
 */
public interface BlogMapper {

    Blog findById(String id);
}
