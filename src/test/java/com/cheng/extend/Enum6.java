package com.cheng.extend;

/**
 * @Author :cheng
 * @Description: 用法六：使用接口组织枚举
 * @Date: created in 13:43 2018/6/23
 * @Reference:
 */
public interface Enum6 {
    enum Coffee implements Enum6{
        BLACK_COFFEE,DECAF_COFFEE,LATTE,CAPPUCCINO
    }
    enum Dessert implements Enum6{
        FRUIT, CAKE, GELATO
    }

}
class Enum6Test{
    public static void main(String[] args) {
        for(Enum6.Dessert dessert:Enum6.Dessert.values()) {
            System.out.println(dessert + " ");
        }
        System.out.println("=====");
        for(Enum6.Coffee coffee: Enum6.Coffee.values()) {
            System.out.println(coffee + " ");
        }
    }
}