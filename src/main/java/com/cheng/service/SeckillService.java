package com.cheng.service;

import com.cheng.dto.Exposer;
import com.cheng.dto.SeckillExecution;
import com.cheng.entity.Seckill;
import com.cheng.exception.RepeatKillException;
import com.cheng.exception.SeckillCloseException;
import com.cheng.exception.SeckillException;

import java.util.List;

/**
 * @Author :cheng
 * @Description: 业务接口：站在使用者角度设计
 * 三个角度：方法定义力度,  参数, 返回类型(return 类型/异常)
 *
 * @Date: created in 9:23 2018/6/23
 * @Reference:
 */
public interface SeckillService {
    /**
     * 查询所有的秒杀记录
     * @return
     */
    List<Seckill> getSeckillList();

    /**
     * 查询单个秒杀记录
     * @param seckillId
     * @return
     */
    Seckill getById(long seckillId);

    /**
     * 秒杀开启是输出秒杀接口地址
     * 否则付出系统时间和秒杀时间
     * @param seckillId
     */
    Exposer /*ToDo*/ exportSeckillUrl(long seckillId);

    /**
     * 执行秒杀
     * @param seckillId
     * @param userPhone
     * @param md5
     */
    SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws SeckillException, RepeatKillException, SeckillCloseException;



}