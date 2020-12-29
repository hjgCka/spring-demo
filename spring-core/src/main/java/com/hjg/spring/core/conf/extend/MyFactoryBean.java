package com.hjg.spring.core.conf.extend;

import org.springframework.beans.factory.FactoryBean;

/**
 * 用于创建复杂类。这个类需要实例化为bean，然后传入一些简单属性。
 * 通过getObject()方法中的代码，构造一个对象返回。
 * @description:
 * @author: hjg
 * @createdOn: 2020/12/29
 */
public class MyFactoryBean implements FactoryBean {

    private String name;
    private int age;

    @Override
    public Object getObject() throws Exception {
        return null;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
