package com.hjg.spring.core.model.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.stream.Stream;

/**
 * @description:
 * @author: hjg
 * @createdOn: 2020/12/29
 */
public class MovieRecommender {

    @Autowired
    @Qualifier("main")
    private MovieCatalog movieCatalog;

    @Autowired
    private MovieCatalog[] catalogs;

    public void printAllCatalog() {
        Stream.of(catalogs).forEach(e -> System.out.println(e.getMaster()));
    }

    public void printTargetCatalog() {
        System.out.println(movieCatalog.getMaster());
    }

}
