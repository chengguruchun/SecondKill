package com.cheng.enums;

/**
 * @Author :cheng
 * @Description: 使用枚举表示常量数据字典
 * @Date: created in 11:11 2018/6/23
 * @Reference:
 */
public enum SeckillStatEnum {
    SUCCESS(1, "秒杀成功"),
    END(0, "秒杀结束"),
    REPEAT_KILL(-1,"重复秒杀"),
    INNER_ERROR(-2, "系统异常"),
    DATA_REWRITE(-3, "数据篡改");
    private int state;
    private String stateInfo;

    SeckillStatEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public int getState() {
        return state;
    }

    public static SeckillStatEnum stateOf(int index) {
        for(SeckillStatEnum state:values()) {
            if (index == state.getState()) {
                return state;
            }
        }

        return null;
    }
}