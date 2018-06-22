package com.cheng.dao;

import com.cheng.entity.SuccessKilled;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * @Author :cheng
 * @Description:
 * @Date: created in 12:06 2018/6/22
 * @Reference:
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit  spring配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKillDaoTest {
    @Resource
    private  SuccessKillDao successKillDao;

    /**
     *  Preparing: INSERT ignore INTO success_killed(seckill_id, user_phone) VALUES (?, ?)
     ==> Parameters: 1000(Long), 13502181181(Long)
     <==    Updates: 0
     * @throws Exception
     */
    @Test
    public void testInsertSuccessKilled() throws Exception {
        long id = 1000L;
        long phone = 13502181181L;
        int insertCount = successKillDao.insertSuccessKilled(id, phone);
        System.out.println("insertCount:" + insertCount);
    }

    @Test
    public void testQueryByIdWithSeckill() throws Exception {
        long id = 1000L;
        long phone = 13502181181L;
        SuccessKilled successKilled = successKillDao.queryByIdWithSeckill(id, phone);
        System.out.println(successKilled);
        System.out.println(successKilled.getSeckillId());
    }
}