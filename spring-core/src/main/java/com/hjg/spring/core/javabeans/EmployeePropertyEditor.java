package com.hjg.spring.core.javabeans;

import java.beans.PropertyChangeListener;
import java.beans.PropertyEditorSupport;

/**
 * @description:
 * @author: hjg
 * @createdOn: 2020/12/31
 */
public class EmployeePropertyEditor extends PropertyEditorSupport {

    @Override
    public void setValue(Object value) {
        super.setValue(value);
    }

    @Override
    public Object getValue() {
        return super.getValue();
    }

    @Override
    public String getAsText() {
        return super.getAsText();
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        String[] array = text.split("\\|");
        Employee employee = new Employee();
        employee.setName(array[0]);
        employee.setSalary(Float.valueOf(array[1]));

        super.setValue(employee);
    }

    @Override
    public synchronized void addPropertyChangeListener(PropertyChangeListener listener) {
        super.addPropertyChangeListener(listener);
    }
}
