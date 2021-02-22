package com.hjg.spring.dynamic.conf;

import com.hjg.spring.dynamic.model.Phone;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 一般配合注解使用，还有ImportSelector等注解。
 * @description:
 * @author: hjg
 * @createdOn: 2021/2/22
 */
public class PhoneBeanDefRegistrar implements ImportBeanDefinitionRegistrar {
    //这里注册的bean也能被aop处理

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        String[] phones = (String[]) importingClassMetadata.getAnnotationAttributes(EnableOtherPhone.class.getName()).get("phones");

        if(phones.length == 0) {
            System.out.println("no phones!1");
        } else {
            for(String name : phones) {
                BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(Phone.class);
                beanDefinitionBuilder.addPropertyValue("name", name);

                registry.registerBeanDefinition(name, beanDefinitionBuilder.getBeanDefinition());
            }
        }
    }
}
