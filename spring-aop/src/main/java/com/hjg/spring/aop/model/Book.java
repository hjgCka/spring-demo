package com.hjg.spring.aop.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Book {
    private String name;
    private String author;
    private LocalDate publishDate;
    private Double price;
}
