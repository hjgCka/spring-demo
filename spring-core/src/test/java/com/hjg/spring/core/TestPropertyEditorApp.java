package com.hjg.spring.core;

import com.hjg.spring.core.javabeans.Company;
import com.hjg.spring.core.javabeans.Employee;
import org.junit.Test;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.PropertyValue;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @description:
 * @author: hjg
 * @createdOn: 2020/12/31
 */
public class TestPropertyEditorApp {

    @Test
    public void beanwrapperTest() {
        BeanWrapper company = new BeanWrapperImpl(new Company());
        //设置并获取属性值
        company.setPropertyValue("name", "James");
        System.out.println(company.getPropertyValue("name"));

        PropertyValue pv = new PropertyValue("name", "Jack");
        company.setPropertyValue(pv);
        System.out.println(company.getPropertyValue("name"));

        //设置和获取嵌套属性
        BeanWrapper jim = new BeanWrapperImpl(new Employee());
        jim.setPropertyValue("name", "Jim");
        company.setPropertyValue("managingDirector", jim.getWrappedInstance());

        System.out.println(company.getPropertyValue("managingDirector.salary"));

        //能够用这个方法设置自定义的PropertyEditor
        //company.registerCustomEditor();
    }

    /**
     * 测试注册自定义属性编辑器。
     * 1，使用JavaBean标准的PropertyEditor注册方式，即通过特定类名进行。
     * 2，使用MyPropertyEditorRegistrar和CustomEditorConfigurer机制进行注册。
     */
    @Test
    public void propertyEditorTest() {
        String path = "classpath:application.xml";
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(path);

        Company company = applicationContext.getBean(Company.class);
        System.out.println(company);
    }
}
