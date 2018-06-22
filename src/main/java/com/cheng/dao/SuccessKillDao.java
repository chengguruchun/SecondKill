package com.cheng.dao;

import com.cheng.entity.SuccessKilled;
import org.apache.ibatis.annotations.Param;

/**
 * @Author :cheng
 * @Description:
 * @Date: created in 9:17 2018/6/22
 * @Reference:
 */
public interface SuccessKillDao {
    /**
     * 插入购买明细， 可过滤重复（设计的联合主键，帮我们过滤主键）
     * @param seckillId
     * @param userPhone
     * @return 插入的行数
     */
    int insertSuccessKilled(@Param("seckillId")long seckillId, @Param("userPhone")long userPhone);

    /**
     *
     * @param seckillId
     * @return
     */
    SuccessKilled queryByIdWithSeckill(@Param("seckillId")long seckillId, @Param("userPhone")long userPhone);
}
