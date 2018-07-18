
# SecondKill
慕课网-秒杀系统

一：可能遇到的问题
1：MySQL 5.4.6之前有关timestamp设置current_timestamp时报错there can be only one TIMESTAMP column with CURRENT_TI（解决方法）
https://blog.csdn.net/wen_t/article/details/70342116

2：MySQL_避免重复插入_IGNORE
https://blog.csdn.net/u010003835/article/details/54381763

3:mybatis 中的<![CDATA[ ]]>
https://blog.csdn.net/qh_java/article/details/50755655

4：MyBatis中#{}和${}的不同和${}的妙用
https://blog.csdn.net/jsloveyou/article/details/79044381
- 一般${}用在我们能够确定值的地方，也就是我们程序员自己赋值的地方。
- 而#{}一般用在用户输入值的地方！

5: java 项目中classpath就是指 java和resource目录下的资源

6：org.springframework.jdbc.CannotGetJdbcConnectionException: Could not get JDBC Connection
- https://blog.csdn.net/sinat_27406925/article/details/70666380

一个整合SSM框架的高并发和商品秒杀项目,学习目前较流行的Java框架组合实现高并发秒杀API

## 项目的来源
项目的来源于国内IT公开课平台慕课网,慕课网上资源有质量没的说,很适合学习一些技术的基础,这个项目是由四个系列的课程组成的,流程分为几个流程,很基础地教你接触到一个相对有技术含量的项目

- Java高并发秒杀API之业务分析与DAO层
- Java高并发秒杀API之web层
- Java高并发秒杀API之Service层
- Java高并发秒杀API之高并发优化
其实这几个流程也就是开发的流程,首先从DAO层开始开发,从后往前开发,开始Coding吧！

## 项目环境的搭建
- 操作系统: Windows 8.1

- IDE:IntelliJ IDEA

- JDK: JDK1.8

- Web容器: Tomcat 7.0

- 数据库:Mysql-5.5.60

- 依赖管理工具: Maven 3.3.9

- 框架: springMvc + spring + mybatis

## IDEA 快捷键
- Ctrl+Shift+N按文件名搜索文件
- Ctrl+N按名字搜索类
- Ctrl+H 查看类的继承关系

## reference:
https://github.com/iamycx/Spring-learning/blob/master/seckill/src/main/resources/mapper/SuccessKilledDao.xml