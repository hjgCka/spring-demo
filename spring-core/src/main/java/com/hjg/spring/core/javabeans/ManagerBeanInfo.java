package com.hjg.spring.core.javabeans;

import lombok.SneakyThrows;

import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

/**
 * 为Manager类自定义一个BeanInfo，可以控制属性的数量。
 * 默认查询出来的BeanInfo会用所有的属性，这可能会很多。
 * 自定义的名称，要在类名后面加上"BeanInfo"
 * @description:
 * @author: hjg
 * @createdOn: 2020/12/31
 */
public class ManagerBeanInfo extends SimpleBeanInfo {

    @Override
    public MethodDescriptor[] getMethodDescriptors() {
        //只保留对应属性的setter和getter

        PropertyDescriptor[] propertyArray = this.getPropertyDescriptors();
        MethodDescriptor[] methodArray = new MethodDescriptor[propertyArray.length * 2];

        for(int i=0; i<propertyArray.length; i++) {
            PropertyDescriptor pd = propertyArray[i];

            int j = 2*i;
            methodArray[j] = new MethodDescriptor(pd.getReadMethod());
            methodArray[j+1] = new MethodDescriptor(pd.getWriteMethod());
        }

        return methodArray;
    }

    @SneakyThrows
    @Override
    public PropertyDescriptor[] getPropertyDescriptors() {
        //只需要name, 和manager的carName bonus
        PropertyDescriptor pd1 = new PropertyDescriptor("carName", Manager.class);
        PropertyDescriptor pd2 = new PropertyDescriptor("bonus", Manager.class);
        PropertyDescriptor pd3 = new PropertyDescriptor("name", Manager.class);

        return new PropertyDescriptor[]{pd1, pd2, pd3};
    }
}
