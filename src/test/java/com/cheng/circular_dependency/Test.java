package com.cheng.circular_dependency;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author :cheng
 * @Description:    spring利用三级缓存来解决循环依赖的问题~~~
 * @Date: created in 13:39 2018/6/24
 * @Reference: https://blog.csdn.net/u010644448/article/details/59108799
 */

/**
 * 循环依赖就是N个类中循环嵌套引用，如果在日常开发中我们用new 对象的方式发生这种循环依赖的话程序会在运行时一直循环
 * 调用，直至内存溢出报错。下面说一下Spring是如果解决循环依赖的。
 */
public class Test {
    //构造器注入，会出现循环依赖的问题
    @org.junit.Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("springContext.xml");
        System.out.println(context.getBean("a", StudentA.class));
    }

    //set注入，解决循环依赖的问题
    @org.junit.Test
    public void test1() {
        ApplicationContext context = new ClassPathXmlApplicationContext("springContext1.xml");
        System.out.println(context.getBean("a", StudentA.class));
    }

    /**
     * 对于“prototype”作用域Bean，Spring容器无法完成依赖注入，因为“prototype”作用域的Bean，Spring容
     器不进行缓存，因此无法提前暴露一个创建中的Bean。
     */
    //prototype也不能解决循环依赖的问题
    @org.junit.Test
    public void test2() {
        ApplicationContext context = new ClassPathXmlApplicationContext("springContext2.xml");
        System.out.println(context.getBean("a", StudentA.class));
    }

}