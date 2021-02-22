package com.hjg.spring.dynamic.conf;

import com.hjg.spring.dynamic.model.Phone;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

import java.util.Date;

/**
 * 这个接口专门用于动态注册Bean，通过描述可知先于BeanFactoryPostProcessor
 * @description:
 * @author: hjg
 * @createdOn: 2021/2/22
 */
public class PhoneBeanDefPostProcessor implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(Phone.class);
        beanDefinitionBuilder.addPropertyValue("name", "Apple");

        registry.registerBeanDefinition("apple", beanDefinitionBuilder.getBeanDefinition());
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(Phone.class, () -> {
            Phone oppo = new Phone();
            oppo.setName("Oppo");
            oppo.setDate(new Date());
            return oppo;
        });

        ((DefaultListableBeanFactory) beanFactory).registerBeanDefinition("Oppo", beanDefinitionBuilder.getBeanDefinition());
    }
}
