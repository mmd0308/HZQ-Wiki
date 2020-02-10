package com.hzqing.admin.model.enums.article;

import com.baomidou.mybatisplus.core.enums.IEnum;

/**
 * 文章的状态
 * @author hzqing
 * @date 2020-02-01 17:12
 */
public enum ArticleState implements IEnum<Integer> {


    /**
     * 文章状态，草稿箱
     */
    DRAFT(1,"草稿"),


    /**
     * 文章状态，发布状态
     */
    RELEASE(2,"发布");


    private Integer value;

    private String desc;

    ArticleState(Integer value, String desc) {
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
