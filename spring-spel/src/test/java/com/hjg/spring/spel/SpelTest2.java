package com.hjg.spring.spel;

import com.hjg.spring.spel.model.model.Inventor;
import com.hjg.spring.spel.model.model.PlaceOfBirth;
import com.hjg.spring.spel.model.model.Society;
import lombok.Getter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.SimpleEvaluationContext;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.*;

/**
 * @description:
 * @author: hjg
 * @createdOn: 2021/1/7
 */
public class SpelTest2 {

    private StandardEvaluationContext standardContext;
    private EvaluationContext simpleEvaluationContext;
    private ExpressionParser parser;
    private Society society;

    @Before
    public void init() {
        standardContext = new StandardEvaluationContext();
        simpleEvaluationContext = SimpleEvaluationContext.forReadOnlyDataBinding().build();
        parser = new SpelExpressionParser();

        society = new Society();
        society.setName("Jack");

        Inventor inventor = new Inventor();
        inventor.setName("Jim");
        inventor.setNationality("USA");
        List<Inventor> list = Arrays.asList(inventor);
        society.setMembers(list);
    }

    /**
     * StandardEvaluationContext实现提供了Spel完整的功能。
     * EvaluationContext可以没有root object，一般getValue会提供root object。
     */
    @Test
    public void rootObjTest() {
        //创建一个root object为null的上下文。
        standardContext.setRootObject(society);

        //#root和#this，一般需要设置变量来使用

        Expression expression = parser.parseExpression("name");
        String name = expression.getValue(standardContext, String.class);
        System.out.println(name);
        Assert.assertEquals(society.getName(), name);
    }

    class Simple {
        @Getter
        private List<Boolean> booleanList = new ArrayList<Boolean>();
    }

    /**
     * 同时支持setValue和getValue
     */
    @Test
    public void simpleContextTest() {

        //**********访问外部类访问内部类时，可以直接访问内部类属性，即使它是private***********
        Simple simple = new Simple();
        simple.booleanList.add(true);
        System.out.println(simple.booleanList);

        Expression expression = parser.parseExpression("booleanList[0]");
        expression.setValue(simpleEvaluationContext, simple, "false");
        System.out.println(simple.booleanList);

        boolean result = expression.getValue(simpleEvaluationContext, simple, Boolean.class);
        Assert.assertEquals(false, result);
    }

    @Test
    public void contextRootTest() {
        List<Integer> primes = new ArrayList<Integer>();
        primes.addAll(Arrays.asList(2,3,5,7,11,13,17));

        simpleEvaluationContext.setVariable("primes", primes);

        //访问变量时采用#
        List<Integer> result =
                (List<Integer>)parser.parseExpression("#primes.?[#this>10]")
                        .getValue(simpleEvaluationContext);
        System.out.println(result);
    }

    @Test
    public void dataBindingTest() {

        //为了使用setValue需要使用forReadWriteDataBinding
        SimpleEvaluationContext simpleEvaluationContext = SimpleEvaluationContext.forReadWriteDataBinding().build();

        Inventor jack = new Inventor();
        jack.setName("Jack");
        PlaceOfBirth pb1 = new PlaceOfBirth();
        pb1.setCountry("USA");
        pb1.setCity("NYC");
        jack.setPlaceOfBirth(pb1);

        Inventor jim = new Inventor();
        jim.setName("Jim");
        PlaceOfBirth pb2 = new PlaceOfBirth();
        pb2.setCountry("UK");
        pb2.setCity("London");
        jim.setPlaceOfBirth(pb2);
        //使用数组
        Inventor[] array = new Inventor[]{jim};

        Map officers = new HashMap();
        officers.put(Society.President, jack);
        officers.put(Society.Advisors,  array);

        society.setOfficers(officers);

        Inventor pupin = parser.parseExpression("Officers['president']").getValue(
                simpleEvaluationContext, society, Inventor.class);
        System.out.println(pupin);

        // evaluates to "Idvor"
        String city = parser.parseExpression("Officers['president'].PlaceOfBirth.City").getValue(
                simpleEvaluationContext, society, String.class);
        System.out.println(city);

        // setting values，如果要使用setValue方法，必须forReadWriteDataBinding来构造SimpleEvaluationContext
        //即使PlaceOfBirth.country字段是public也无法使用setValue方法
        parser.parseExpression("Officers['advisors'][0].PlaceOfBirth.Country").setValue(
                simpleEvaluationContext, society, "Croatia");
        System.out.println(jim);
    }

    @Test
    public void templateSpelTest() {
        String randomPhrase = parser.parseExpression(
                "random number is #{T(java.lang.Math).random()}",
                new TemplateParserContext()).getValue(String.class);
        System.out.println(randomPhrase);
    }
}
