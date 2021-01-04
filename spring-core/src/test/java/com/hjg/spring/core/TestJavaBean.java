package com.hjg.spring.core;

import com.hjg.spring.core.javabeans.*;
import org.junit.Test;
import org.springframework.util.StringUtils;

import java.beans.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: hjg
 * @createdOn: 2020/12/31
 */
public class TestJavaBean {

    @Test
    public void beanInfoTest() throws IntrospectionException {
        BeanInfo beanInfo = JavaBeansUtil.getClassBeanInfo(Employee.class);
        JavaBeansUtil.printBeanInfo(beanInfo);

        System.out.println("\n");
        BeanDescriptor beanDescriptor = beanInfo.getBeanDescriptor();
        beanDescriptor.setValue("kkkk", "jjjjj");
        JavaBeansUtil.printGlobalBeanAttr(beanInfo);

        System.out.println("\n\n\ncompany");
        BeanInfo company = JavaBeansUtil.getClassBeanInfo(Company.class);
        JavaBeansUtil.printBeanInfo(company);

        //自动关联到ManagerBeanInfo
        System.out.println("\n\n\nmanager");
        BeanInfo manager = JavaBeansUtil.getClassBeanInfo(Manager.class);
        JavaBeansUtil.printBeanInfo(manager);

        System.out.println("\n\n\nManagerBeanInfo");
        JavaBeansUtil.printBeanInfo(new ManagerBeanInfo());
    }

    @Test
    public void testPropertyEditor() throws IntrospectionException {
        //属性编辑器与属性进行关联，有3种方法，推荐如下的第一种方法
        PropertyEditorManager.registerEditor(Employee.class, EmployeeEditor.class);

        /*
        第二种通过pd进行设置
        PropertyDescriptor pd = new PropertyDescriptor("managingDirector", Company.class);
        pd.setPropertyEditorClass(EmployeePropertyEditor.class);
        */

        /**
         *  第三种通过名称搜索，这种方式避免注册代码。
         *  1，添加Editor到对应类名的，全限定类名之后。
         *  2，添加Editor到类名之后，并且搜索classpath
         */

        Map<String, String> params = new HashMap<>();
        params.put("name", "IBM");
        params.put("managingDirector", "James|5000");

        //每个Company对象都有一个PropertyEditor
        Company company = JavaBeansUtil.convert2Company(params);
        System.out.println(company);
    }

    //TODO 如何对对象进行设置和读取？通过BeanInfo对象获得PropertyDescriptor，可以获得读写的method，调用反射即可。

    @Test
    public void listenerTest() {
        Manager manager = new Manager();

        try {
            manager.setName("Jack");
            manager.setBonus("5000");
            manager.setCarName("Benz");

            //为manager对象的bonus属性添加更新监听器
            manager.addPropertyChangeListener("bonus", evt -> {
                String propertyName = evt.getPropertyName();
                String oldValue = (String)evt.getOldValue();
                String newValue = (String)evt.getNewValue();

                System.out.println("propertyName = " + propertyName + ", oldValue = " + oldValue + ", newValue = " + newValue);
            });

            //为manager对象的carName属性添加否决监听器
            manager.addVetoListener("carName", evt -> {
                String propertyName = evt.getPropertyName();
                String oldValue = (String)evt.getOldValue();
                String newValue = (String)evt.getNewValue();

                System.out.println("propertyName = " + propertyName + ", oldValue = " + oldValue + ", newValue = " + newValue);

                if("Benz".equals(oldValue) && !StringUtils.isEmpty(newValue)) {
                    //如果oldValue已经是Benz了，就不再更新
                    throw new PropertyVetoException("已经是Benz了，不可更新", evt);
                }
            });

            manager.setBonus("6000");
            manager.setCarName("TOYTO");
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(manager);
    }
}
