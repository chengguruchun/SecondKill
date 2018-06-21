-- 数据库初始化脚本

-- 创建数据库
CREATE database seckill;
-- 使用数据库
use seckill;

CREATE TABLE seckill(
   `seckill_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '商品库存id' ,
    `name` VARCHAR(120) NOT NULL COMMENT '商品名称',
    `number` INT NOT NULL COMMENT '库存数量',
    `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间',
    `start_time` TIMESTAMP NOT NULL COMMENT '秒杀开始时间',
    `end_time` TIMESTAMP NOT NULL COMMENT '秒杀结束时间',

    PRIMARY KEY (seckill_id),
    KEY idx_start_time(start_time),
    KEY idx_end_time(end_time),
    KEY idx_create_time(create_time)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT =

