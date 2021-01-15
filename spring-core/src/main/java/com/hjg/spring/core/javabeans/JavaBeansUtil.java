package com.hjg.spring.core.javabeans;

import java.beans.*;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @description:
 * @author: hjg
 * @createdOn: 2020/12/31
 */
public class JavaBeansUtil {

    /**
     * 获取BeanInfo对象，这个对象对于某个Class来说是全局的。
     * @param cls
     * @param <T>
     * @return
     * @throws IntrospectionException
     */
    public static <T> BeanInfo getClassBeanInfo(Class<T> cls) throws IntrospectionException {
        /**
         * Introspector.USE_ALL_BEANINFO
         * Introspector.IGNORE_IMMEDIATE_BEANINFO
         * Introspector.IGNORE_ALL_BEANINFO
         *
         * 通过查看Introspector的文档，默认情况下如果Bean有一个对应的'Bean'BeanInfo类存在，
         * 那么就会使用这个类作为BeanInfo。
         * 这种行为通过Introspector的3个标志进行控制。
         */
        BeanInfo beanInfo = Introspector.getBeanInfo(cls, Introspector.USE_ALL_BEANINFO);
        return beanInfo;
    }

    /**
     * 打印这个BeanInfo的全局的属性信息。
     * @param beanInfo
     */
    public static void printGlobalBeanAttr(BeanInfo beanInfo) {
        BeanDescriptor beanDescriptor = beanInfo.getBeanDescriptor();

        Enumeration<String> attrs = beanDescriptor.attributeNames();
        while(attrs.hasMoreElements()) {
            String attr = attrs.nextElement();
            System.out.println(beanDescriptor.getValue(attr));
        }
    }

    /**
     * 打印BeanInfo的属性信息 和 方法信息。
     * @param beanInfo
     */
    public static void printBeanInfo(BeanInfo beanInfo) {
        //打印方法、属性
        MethodDescriptor[] methodDescriptors = beanInfo.getMethodDescriptors();
        for(int i=0; i<methodDescriptors.length; i++) {
            MethodDescriptor md = methodDescriptors[i];

            String params = "empty";
            ParameterDescriptor[] pds = md.getParameterDescriptors();
            if(pds != null && pds.length > 0) {
                params = Arrays.stream(pds).map(pd -> pd.getName()).collect(Collectors.joining(","));
            }

            System.out.println("i=" + i + ", method = " + md.getName() + ", params = " + params);
        }

        PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
        for(int i=0; i<descriptors.length; i++) {
            PropertyDescriptor pd = descriptors[i];
            System.out.println("i=" + i + ", property = " + pd.getName() + ", type = " + pd.getPropertyType().getName());
        }
    }

    /**
     * 将Map<String, String>转换为Company对象
     * @param params
     * @return
     * @throws IntrospectionException
     */
    public static Company convert2Company(Map<String, String> params) throws IntrospectionException {
        Company company = new Company();

        BeanInfo beanInfo = Introspector.getBeanInfo(Company.class);

        PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
        Stream.of(descriptors).forEach(propertyDesc -> {
            String propertyName = propertyDesc.getName();

            if(params.containsKey(propertyName)) {
                String value = params.get(propertyName);

                Class cls = propertyDesc.getPropertyType();
                Method method = propertyDesc.getWriteMethod();

                if(cls == String.class) {
                    try {
                        method.invoke(company, value);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (cls == Employee.class) {
                    //必须要为Employee设置PropertyEditor.
                    PropertyEditor propertyEditor = PropertyEditorManager.findEditor(cls);
                    propertyEditor.setAsText(value);

                    try {
                        method.invoke(company, propertyEditor.getValue());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            } else {
                System.out.println("未找到对应属性:" + propertyName);
            }
        });

        return company;
    }
}
