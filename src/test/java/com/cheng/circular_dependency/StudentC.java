package com.cheng.circular_dependency;

/**
 * @Author :cheng
 * @Description: 循环依赖的话，会造成内存溢出啊
 * @Date: created in 13:36 2018/6/24
 * @Reference:
 */
public class StudentC {
    private StudentA studentA;

    public StudentC(StudentA studentA) {
        this.studentA = studentA;
    }

    public StudentC() {

    }

    public void setStudentA(StudentA studentA) {
        this.studentA = studentA;
    }


}