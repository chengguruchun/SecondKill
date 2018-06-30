package com.cheng.service.impl;

import com.cheng.dao.SeckillDao;
import com.cheng.dao.SuccessKillDao;
import com.cheng.dao.cache.RedisDao;
import com.cheng.dto.Exposer;
import com.cheng.dto.SeckillExecution;
import com.cheng.entity.Seckill;
import com.cheng.entity.SuccessKilled;
import com.cheng.enums.SeckillStatEnum;
import com.cheng.exception.RepeatKillException;
import com.cheng.exception.SeckillCloseException;
import com.cheng.exception.SeckillException;
import com.cheng.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * @Author :cheng
 * @Description:  秒杀业务实现类
 * @Date: created in 10:48 2018/6/23
 * @Reference:
 */

@Service
public class SeckillServiceImpl implements SeckillService{

    private Logger logger = LoggerFactory.getLogger(SeckillServiceImpl.class);

    //注入service依赖
    //按类型注入
    @Autowired
    private SeckillDao seckillDao;
    @Autowired
    private RedisDao redisDao;

    @Autowired
    private SuccessKillDao successKillDao;

    //md5不可逆的加密算法，用于混淆md5
    private final String slat = "djsfjIOIOIOflkvdf";
    @Override
    public List<Seckill> getSeckillList() {

        return seckillDao.queryAll(0, 4);
    }

    @Override
    public Seckill getById(long seckillId) {

        return seckillDao.queryById(seckillId);
    }

    @Override
    public Exposer exportSeckillUrl(long seckillId) {

        //优化点：
        /**
         * get from cache
         *  if null
         *    get db
         *    else
         *      put cache
         *
         */

        //访问redis
        Seckill seckill = redisDao.getSeckill(seckillId);
        if (seckill == null) {
            //第二部：查数据库
             seckill = seckillDao.queryById(seckillId);
            if (seckill == null) {

                return new Exposer(false, seckillId);
            }else {
                //第三部：插入到数据库中
                redisDao.putSeckill(seckill);
            }
        }

        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        //得到当前系统的时间
        Date nowTime = new Date();
        if (nowTime.getTime() < startTime.getTime() || nowTime.getTime() > endTime.getTime()) {
            return new Exposer(false, seckillId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
        }

        //转化指定字符串的过程
        String md5 = this.getMd5(seckillId);//ToDo
        return new Exposer(true, md5, seckillId);
    }

    //生成md5，用于加密
    private String getMd5(long seckillId) {
        String base = seckillId + "/" + slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());

        return md5;
    }

    /**
     * 使用注解的优点：
     * 1.开发团队达成一致约定
     * 2.保证事务方法执行时间尽可能短，不要穿插其他的耗时操作，比如RPC/HTTP请求
     * 3.不是所有的方法都需要事务，如单个查询等等
     *
     * @param seckillId
     * @param userPhone
     * @param md5
     * @return
     * @throws SeckillException
     * @throws RepeatKillException
     * @throws SeckillCloseException
     */
    @Transactional
    @Override
    public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws SeckillException, RepeatKillException, SeckillCloseException {
        if (md5 == null || !md5.equals(this.getMd5(seckillId))) {
            System.out.println(md5 + "----" + this.getMd5(seckillId));
            throw new SeckillException("seckill data rewrite");
        }

        //执行秒杀逻辑:减库存，+ 记录购买行为
        Date nowTime = new Date();
        try {
            int updateCount = seckillDao.reduceNumber(seckillId, nowTime);
            if (updateCount <= 0) {
                //没有更新到记录，秒杀结束
                throw new SeckillCloseException("seckill is closed");
            }else {
                //记录购买行为
                int insertCount = successKillDao.insertSuccessKilled(seckillId, userPhone);
                //唯一：seckillId, userPhone 联合主键
                if (insertCount <= 0) {
                    throw new RepeatKillException("seckill repeat");
                }else {
                    //秒杀成功
                    SuccessKilled successKilled = successKillDao.queryByIdWithSeckill(seckillId, userPhone);

                    return new SeckillExecution(seckillId, SeckillStatEnum.SUCCESS, successKilled);
                }
            }
        }catch (SeckillCloseException e1) {
            throw e1;
        }catch (RepeatKillException e2){
            throw e2;
        }catch (Exception e) {
            //所有编译期异常，转化为运行期异常
            throw new SeckillException("seckill inner error:" + e.getMessage());
        }
    }
}