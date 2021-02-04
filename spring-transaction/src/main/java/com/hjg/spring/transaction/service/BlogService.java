package com.hjg.spring.transaction.service;

import com.hjg.spring.transaction.model.Blog;

import java.util.List;

/**
 * @description:
 * @author: hjg
 * @createdOn: 2021/2/4
 */
public interface BlogService {

    Blog getBlogById(String id);

    List<Blog> getAllBlogs();

    Blog saveAndUpdate(Blog blog, String newTitle);
}
