package com.hjg.spring.core.conf.editor;

import com.hjg.spring.core.javabeans.Employee;
import com.hjg.spring.core.javabeans.EmployeeEditor;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;

/**
 * @description:
 * @author: hjg
 * @createdOn: 2021/1/4
 */
public class MyPropertyEditorRegistrar implements PropertyEditorRegistrar {


    @Override
    public void registerCustomEditors(PropertyEditorRegistry registry) {
        //可以注册多个自定义属性编辑器，但是对于EmployeePropertyEditor来说，可以使用默认机制，而不用注册
        registry.registerCustomEditor(Employee.class, new EmployeeEditor());
    }
}
