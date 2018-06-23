package com.cheng.exception;

/**
 * @Author :cheng
 * @Description: 秒杀关闭异常
 * @Date: created in 10:42 2018/6/23
 * @Reference:
 */
public class SeckillCloseException extends SeckillException{
    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }

}