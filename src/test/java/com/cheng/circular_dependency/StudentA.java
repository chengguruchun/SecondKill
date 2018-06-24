package com.cheng.circular_dependency;

/**
 * @Author :cheng
 * @Description:
 * @Date: created in 13:35 2018/6/24
 * @Reference:
 */
public class StudentA {
    private StudentB studentB;

    public StudentA(StudentB studentB) {
        this.studentB = studentB;
    }
    public StudentA() {

    }

    public void setStudentB(StudentB studentB) {
        this.studentB = studentB;
    }
}