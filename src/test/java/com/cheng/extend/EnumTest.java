package com.cheng.extend;

/**
 * @Author :cheng
 * @Description: 枚举类的使用举例
 * @Date: created in 13:26 2018/6/23
 * @Reference:
 */

/**
 * 枚举类继承了  java.lang.enum 类，并且是final，所以不能继承了
 *
 * 之后定义的元素 反编译时候会为每个枚举类型创建一个实例，并且确保是单例的~~线程安全的
 */

/**
 * //反编译Day.class
 final class Day extends Enum
 {
 //编译器为我们添加的静态的values()方法
 public static Day[] values()
 {
 return (Day[])$VALUES.clone();
 }
 //编译器为我们添加的静态的valueOf()方法，注意间接调用了Enum也类的valueOf方法
 public static Day valueOf(String s)
 {
 return (Day)Enum.valueOf(com/zejian/enumdemo/Day, s);
 }
 //私有构造函数
 private Day(String s, int i)
 {
 super(s, i);
 }
 //前面定义的7种枚举实例
 public static final Day MONDAY;
 public static final Day TUESDAY;
 public static final Day WEDNESDAY;
 public static final Day THURSDAY;
 public static final Day FRIDAY;
 public static final Day SATURDAY;
 public static final Day SUNDAY;
 private static final Day $VALUES[];

 static
 {
 //实例化枚举实例
 MONDAY = new Day("MONDAY", 0);
 TUESDAY = new Day("TUESDAY", 1);
 WEDNESDAY = new Day("WEDNESDAY", 2);
 THURSDAY = new Day("THURSDAY", 3);
 FRIDAY = new Day("FRIDAY", 4);
 SATURDAY = new Day("SATURDAY", 5);
 SUNDAY = new Day("SUNDAY", 6);
 $VALUES = (new Day[] {
 MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
 });
 }
 }
 */
public class EnumTest {

    private enum Color{
        RED, GREEN, BLACK, YELLOW
    }

    private enum Color1{
        //如果打算自定义自己的方法，那么必须在enum实例序列的最后添加一个分号。而且 Java 要求必须先定义 enum 实例。
        RED("红色", 1), GREEN("绿色", 2), BLANK("白色", 3), YELLO("黄色", 4);
        private String name;
        private int index;
        private Color1(String name, int index) {
            this.name = name;
            this.index = index;
        }

        //普通方法
        public static String getName(int index) {
            for(Color1 color1: values()) {
                if (color1.index == index) {

                    return color1.getName();
                }
            }

            return null;
        }
        //// get set 方法
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        //覆盖方法
        @Override
        public String toString() {
            return this.index+"_" + this.name;
        }
    }
    public static void main(String[] args) {
        //Color.BLACK;
        System.out.println(Color.BLACK);
        System.out.println(Color1.getName(1));
        System.out.println(Color1.GREEN);
    }
}