package com.hjg.spring.transaction.mapper;

import com.hjg.spring.transaction.model.Blog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description:
 * @author: hjg
 * @createdOn: 2021/2/4
 */
public interface BlogMapper {

    Blog findById(String id);

    Blog findByTitle(String title);

    List<Blog> queryBlogs();

    void insertBlog(Blog blog);

    void insertBlogList(List<Blog> list);

    void updateBlog(@Param("blog") Blog blog, @Param("oldTitle") String oldTitle);
}
