package com.hjg.spring.core.model;

import lombok.Data;
import org.springframework.context.Lifecycle;

import javax.annotation.PreDestroy;

/**
 *
 * 如果只实现Lifecycle接口，不会打印任何语句。
 * 因为只有显式地进行stop和start通知才会调用。
 *
 * ConfigurableApplicationContext cAppContext = (ConfigurableApplicationContext)applicationContext;
 *         cAppContext.close();cAppContext.stop();都能触发stop方法。
 *          cAppContext.start();能触发start方法
 *
 * 如果想控制刷新和启动时的行为，可以使用SmartLifecycle接口。
 * @description:
 * @author: hjg
 * @createdOn: 2020/12/28
 */
@Data
public class Person implements Lifecycle {
    private String name;
    private int age;

    @PreDestroy
    public void personDestroy() {
        System.out.println("person pre-destroy...");
    }

    @Override
    public void start() {
        System.out.println("person start...");
    }

    @Override
    public void stop() {
        System.out.println("person stop...");
    }

    /**
     * 判断是否关闭和启动成功了一个bean。
     * 调用cAppContext.stop()和cAppContext.close()时，会执行stop()方法。
     * 当返回true时表示没有被关闭，cAppContext.start()时不会执行start()方法。
     * 当返回false时表示已经关闭了，cAppContext.start()时会执行start()方法。
     * @return
     */
    @Override
    public boolean isRunning() {
        return false;
    }
}
