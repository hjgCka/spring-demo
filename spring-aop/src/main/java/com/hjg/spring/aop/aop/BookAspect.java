package com.hjg.spring.aop.aop;

import com.hjg.spring.aop.model.Book;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.SimpleEvaluationContext;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

@Aspect
public class BookAspect {

    private static final LocalVariableTableParameterNameDiscoverer parameterNameDiscoverer =
            new LocalVariableTableParameterNameDiscoverer();

    //这里填接口的路径和方法，或者填实现类的路径和方法，都是可以的
    @Pointcut("execution(* com.hjg.spring.aop.service.BookService.findByName(String)) && args(name)")
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

    //只针对具体某个方法，而不是针对某个注解
    @Pointcut("execution(* com.hjg.spring.aop.service.BookService.updateBookAll(com.hjg.spring.aop.model.Book, String, String)) && args(book, name, author) ")
    public void updateBook(Book book, String name, String author){}

    @AfterReturning("updateBook(book, name, author)")
    public void afterUpdateBook(Book book, String name, String author) {

    }

    //切点表达式@annotation(cacheUpdate)中，cacheUpdate是小写
    @Pointcut("@annotation(cacheUpdate)")
    public void updateAnnotation(CacheUpdate cacheUpdate){}

    @AfterReturning("updateAnnotation(cacheUpdate)")
    public void afterUpdateAnnotation(JoinPoint joinPoint, CacheUpdate cacheUpdate) {
        String author = cacheUpdate.author();
        String name = cacheUpdate.name();

        if(!StringUtils.isEmpty(author)) {
            if(author.startsWith("#")) {
                author = this.parseArg(joinPoint, author);
            }
        }

        if(!StringUtils.isEmpty(name)) {
            if(name.startsWith("#")) {
                name = this.parseArg(joinPoint, name);
            }
        }

        System.out.println("从AOP中获取了参数author=" + author + ", name=" + name);
    }

    private String parseArg(JoinPoint joinPoint, String spel) {
        String methodName = joinPoint.getSignature().getName();

        //获取被代理的对象的Class对象
        Class cls = joinPoint.getTarget().getClass();

        Method targetMethod = null;
        Method[] methods = cls.getMethods();
        for(Method method : methods) {
            if(method.getName().equals(methodName) && method.isAnnotationPresent(CacheUpdate.class)) {
                targetMethod = method;
                break;
            }
        }

        if(targetMethod == null) {
            return null;
        }

        /**
         * 读取class字节码，解析其中中的LocalVariableTable，得到方法的参数名称。
         * 如果是动态代理出来的类，拿不到方法参数名称(注解用在动态代理类上，无法获取参数)。
         */
        String[] params = parameterNameDiscoverer.getParameterNames(targetMethod);
        //获取被通知的方法的参数值列表
        Object[] objects = joinPoint.getArgs();

        EvaluationContext context = SimpleEvaluationContext.forReadWriteDataBinding().build();
        for(int i=0; i<params.length; i++) {
            context.setVariable(params[i], objects[i]);
            context.setVariable("p"+i, objects[i]);
        }

        ExpressionParser parser = new SpelExpressionParser();
        return parser.parseExpression(spel).getValue(context, String.class);
    }
}
