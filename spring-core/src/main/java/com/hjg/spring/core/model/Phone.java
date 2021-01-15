package com.hjg.spring.core.model;

import lombok.Getter;
import lombok.ToString;

import java.beans.ConstructorProperties;
import java.math.BigDecimal;

/**
 * @description:
 * @author: hjg
 * @createdOn: 2021/1/15
 */
@ToString
public class Phone {

    @Getter
    private String name;
    @Getter
    private BigDecimal price;

    /**
     * 如果参数名称与xmldebean配置构造器名称对得上，可以不加上@ConstructorProperties。
     *即name -> name, name2 -> name2。
     *
     * 如果使用了@ConstructorProperties注解，Spring会使用这个注解查找构造器方法的参数名称。
     * 将这个注解的值与xml配置的constructor-arg的name匹配起来。
     * 如果双方的构造器参数名称无法匹配就会报错。
     *
     * TestCoreApp.phoneTest测试用例。
     *
     * @param name
     * @param price
     */
    @ConstructorProperties({"name2", "price2"})
    public Phone(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }
}
