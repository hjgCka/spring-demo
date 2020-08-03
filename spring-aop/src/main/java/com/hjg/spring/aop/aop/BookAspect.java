package com.hjg.spring.aop.aop;

import com.hjg.spring.aop.model.Book;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class BookAspect {

    //这里填接口的路径和方法，或者填实现类的路径和方法，都是可以的
    @Pointcut("execution(* com.hjg.spring.aop.service..BookService.findByName(String)) && args(name)")
    public void findBook(String name){}

    //如果不需要返回值，方法签名中返回类型为void
    @Around("findBook(name)")
    public Book aroundFindBook(ProceedingJoinPoint joinPoint, String name) {
        try {
            System.out.println("before findBook, name = " + name);
            Object result = joinPoint.proceed(new Object[]{name});
            System.out.println("after findBook, book = " + result);

            return (Book)result;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }
}
