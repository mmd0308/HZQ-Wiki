package com.hzqing.admin.model.enums.doc;

import com.baomidou.mybatisplus.core.enums.IEnum;

/**
 * 文档的访问级别
 * @author hzqing
 * @date 2020-02-02 08:23
 */
public enum DocVisitLevel implements IEnum<Integer> {

    PRIVATE(1,"私有文档"),

    PUBLIC(2,"公开文档");

    private int value;

    private String desc;

    DocVisitLevel(int value, String desc) {
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
