package com.cheng.extend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author :cheng
 * @Description:
 * @Date: created in 14:00 2018/6/23
 * @Reference:
 */
public class DayDemo {
    private final static Logger logger = LoggerFactory.getLogger(DayDemo.class);

    public static final int MONDAY = 1;
    public static final int TUESDAY = 2;
    public static final int WEDNESDAY = 3;
    public static final int THURSDAY = 4;
    public static final int FRIDAY = 5;
    public static final int SATURDAY = 6;
    public static final int SARUDAY1 = 6;
    public static final int SUNDAY = 7;
    public static void main(String[] args) {
        logger.info("解决不了重复值 -{}" , DayDemo.SATURDAY);
        logger.info("解决不了重复值 -{}", DayDemo.SARUDAY1);
    }

}