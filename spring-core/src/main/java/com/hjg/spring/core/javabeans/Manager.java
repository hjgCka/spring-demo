package com.hjg.spring.core.javabeans;

import lombok.Getter;
import lombok.ToString;

import java.beans.*;

/**
 * @description:
 * @author: hjg
 * @createdOn: 2020/12/31
 */
@ToString
public class Manager extends Employee{

    @Getter
    private String carName;

    @Getter
    private String bonus;

    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    private VetoableChangeSupport vcs = new VetoableChangeSupport(this);

    /**
     * Veto监听器通过抛出异常来阻止更新，所以需要在赋值语句之前。
     * @param carName
     * @throws PropertyVetoException
     */
    public void setCarName(String carName) throws PropertyVetoException {
        String oldValue = this.carName;
        vcs.fireVetoableChange("carName", oldValue, carName);

        this.carName = carName;
    }

    /**
     * PropertyChangeListener无法阻止更新，只是打印参数而已。
     * @param bonus
     */
    public void setBonus(String bonus) {
        String oldValue = this.bonus;
        this.bonus = bonus;
        pcs.firePropertyChange("bonus", oldValue, bonus);
    }

    /**
     * 为所有或某个属性进行注册。
     * @param listener
     */
    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(propertyName, listener);
    }

    public void removePorpertyChangeListener(String propertyName, PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(propertyName, listener);
    }

    /**
     * 为所有或某个属性进行注册。
     * @param listener
     */
    public void addVetoListener(String propertyName, VetoableChangeListener listener) {
        vcs.addVetoableChangeListener(propertyName, listener);
    }

    public void removeVetoListener(String propertyName, VetoableChangeListener listener) {
        vcs.removeVetoableChangeListener(propertyName, listener);
    }
}
