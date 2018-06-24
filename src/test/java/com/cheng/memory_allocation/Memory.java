package com.cheng.memory_allocation;

/**
 * @Author :cheng
 * @Description: 分析见reference
 * @Date: created in 14:20 2018/6/24
 * @Reference: https://segmentfault.com/q/1010000012649266
 */

/**
 Heap space 堆空间: 所有存活的对象在此分配.
 Stack space 栈空间: 方法调用时保存变量对象的引用或变量实例.
 Perm space : 保存已加载的类的信息.
 如:
 Student std = new Student();
 执行后内存情况如下:

 Heap堆: 保存 "new Student()"
 Stack栈: 保存实例变量信息 "std"
 Perm Space: 保存类 Student 的信息.


 =========================
 在java中只要是成员变量，一旦它所在类被实例化后，都是放在堆内存的，不管它是不是基础类型。
 局部变量才是放在栈内存的。所以java比其他语言内存安全的多。
 */
public class Memory {

    public static void main(String[] args) { // Line 1
        int i = 1; // Line 2
        Object obj = new Object(); // Line 3
        Memory mem = new Memory(); // Line 4
        mem.foo(obj); // Line 5
    } // Line 9

    private void foo(Object param) { // Line 6
        String str = param.toString(); //// Line 7
        System.out.println(str);
        System.out.println(str == str.intern());  // Line 8
    }
}