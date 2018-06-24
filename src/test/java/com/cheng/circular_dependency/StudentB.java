package com.cheng.circular_dependency;

/**
 * @Author :cheng
 * @Description:
 * @Date: created in 13:36 2018/6/24
 * @Reference:
 */
public class StudentB {
    private StudentC studentC;

    public StudentB(StudentC studentC) {
        this.studentC = studentC;
    }

    public StudentB() {

    }

    public void setStudentC(StudentC studentC) {
        this.studentC = studentC;
    }
}