package com.hzqing.admin.model.enums.space;

import com.baomidou.mybatisplus.core.enums.IEnum;

/**
 * 空间的访问级别
 * @author hzqing
 * @date 2020-02-02 08:23
 */
public enum SpaceVisitLevel implements IEnum<Integer> {

    PRIVATE(1,"私有空间"),

    PUBLIC(2,"公开空间");

    private Integer value;

    private String desc;

    SpaceVisitLevel(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }

    public String getDesc() {
        return desc;
    }
}
