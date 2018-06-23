package com.cheng.extend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author :cheng
 * @Description: 所有的枚举都继承自java.lang.Enum类。由于Java 不支持多继承，所以枚举对象不能再继承其他类。
 * @Date: created in 13:39 2018/6/23
 * @Reference: 用法五：实现接口
 */
interface Behaviour {
    void print();
    String getInfo();
}
public enum Enum5 implements Behaviour {

    RED("红色", 1), GREEN("绿色", 2), BLANK("白色", 3), YELLO("黄色", 4);
    // 成员变量
    private String name;
    private int index;

    // 构造方法
    private Enum5(String name, int index) {
        this.name = name;
        this.index = index;
    }

    //接口方法
    @Override
    public String getInfo() {
        return this.name;
    }

    //接口方法
    @Override
    public void print() {
        System.out.println(this.index + ":" + this.name);
    }

    private final static Logger logger = LoggerFactory.getLogger(DayDemo.class);
    public static void main(String[] args) {
        logger.info("得到属性的值:  {} ", Enum5.BLANK.getInfo());
    }
}
