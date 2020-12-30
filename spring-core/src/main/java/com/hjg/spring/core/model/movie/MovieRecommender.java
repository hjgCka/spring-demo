package com.hjg.spring.core.model.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;

import java.util.stream.Stream;

/**
 * @description:
 * @author: hjg
 * @createdOn: 2020/12/29
 */
public class MovieRecommender implements ResourceLoaderAware {

    @Autowired
    @Qualifier("main")
    private MovieCatalog movieCatalog;

    private ResourceLoader resourceLoader;

    @Autowired
    private MovieCatalog[] catalogs;

    public void printAllCatalog() {
        Stream.of(catalogs).forEach(e -> System.out.println(e.getMaster()));
    }

    public void printTargetCatalog() {
        System.out.println(movieCatalog.getMaster());
    }

    /**
     * ResourceLoaderAware用于需要额外配置文件进行某些操作的bean。
     */
    public void loadResource() {
        //resourceLoader.getResource()
    }

    /**
     * 这里的ResourceLoader，实际上是ApplicationContext
     * @param resourceLoader
     */
    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
