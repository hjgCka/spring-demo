package com.hjg.spring.core.conf.extend;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;

/**
 * BeanPostProcessor只作用于当前容器。
 * @description:
 * @author: hjg
 * @createdOn: 2020/12/29
 */
public class MyPostProcessor implements BeanPostProcessor, Ordered {

    /**
     * 先于afterPropertiesSet方法或init-method方法。
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
