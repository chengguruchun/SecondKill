package com.cheng.exception;

/**
 * @Author :cheng
 * @Description: 秒杀所有的异常~
 * @Date: created in 10:44 2018/6/23
 * @Reference:
 */
public class SeckillException extends RuntimeException{
    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}