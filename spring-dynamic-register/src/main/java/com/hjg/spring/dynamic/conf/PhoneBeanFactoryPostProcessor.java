package com.hjg.spring.dynamic.conf;

import com.hjg.spring.dynamic.model.Phone;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

import java.util.Date;

/**
 * @description:
 * @author: hjg
 * @createdOn: 2021/2/22
 */
public class PhoneBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) beanFactory;

        //注册Bean Definition
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(Phone.class);
        beanDefinitionBuilder.addPropertyValue("name", "Xiaomi");

        //getBeanDefinition方法与getRawBeanDefinition的不同之处在于，它会先执行validate。
        defaultListableBeanFactory.registerBeanDefinition("xiaomi", beanDefinitionBuilder.getBeanDefinition());

        //直接注册实例作为bean
        //由于BeanFactoryPostProcessor在启动时会立刻执行，此时注册的bean可以被BeanPostProcessor处理。即能被动态代理。
        Phone huawei = new Phone();
        huawei.setName("Huawei");
        huawei.setDate(new Date());
        beanFactory.registerSingleton("huawei", huawei);
    }
}
