package com.cheng.dao;

import com.cheng.entity.Seckill;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Date;
import java.util.List;

/**
 * @Author :cheng
 * @Description:
 * @Date: created in 11:18 2018/6/22
 * @Reference:
 */

/**
 * 配置spring和junit整合, Junit 启动时候加载springIoc 容器
 * spring-test， junit
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit  spring配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest extends TestCase {

    @Resource
    private SeckillDao seckillDao;

    String url="jdbc:mysql://localhost:3306/seckill";
    String name="root";
    String pwd="neng";
    @Test
    public void closeAll() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(url, name, pwd);
        System.out.println(conn);
    }

    @Test
    public void testQueryById() throws Exception {
        long id = 1000;
        Seckill seckill = seckillDao.queryById(id);
        System.out.println(seckill.getName());
        System.out.println(seckill);
        /**
         * 1000元秒杀iPhoneX
         * Seckill{seckillId=1000, name='1000元秒杀iPhoneX', number=100,
         * startTime=Sun Jun 24 00:00:00 CST 2018, endTime=Mon Jun 25 00:00:00 CST 2018, createTime=Fri Jun 22 09:01:10 CST 2018}
         */
    }

    @Test
    public void testQueryAll() throws Exception {
        List<Seckill> seckills = seckillDao.queryAll(0, 4);
        for (Seckill seckill : seckills) {
            System.out.println(seckill.getName());
            System.out.println(seckill);
        }
    }

    @Test
    public void testReduceNumber() throws Exception {
        Date killTime = new Date();
        int updateCount = seckillDao.reduceNumber(1000L, killTime);
        System.out.println("updateCount:" + updateCount);
    }


}