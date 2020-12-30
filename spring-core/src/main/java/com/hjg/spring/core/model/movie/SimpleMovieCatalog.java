package com.hjg.spring.core.model.movie;

import lombok.Getter;
import lombok.Setter;

/**
 * @description:
 * @author: hjg
 * @createdOn: 2020/12/29
 */
public class SimpleMovieCatalog extends MovieCatalog{

    @Setter
    @Getter
    private String master;

    public SimpleMovieCatalog(String master) {
        this.master = master;
    }

    @Override
    public String getMaster() {
        return master;
    }
}
