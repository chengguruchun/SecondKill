package com.cheng.exception;

/**
 * @Author :cheng
 * @Description: spring事务 一般只捕获运行期异常
 * 重复秒杀异常
 * @Date: created in 10:40 2018/6/23
 * @Reference:
 */
public class RepeatKillException extends SeckillException{
    public RepeatKillException(String message){
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}