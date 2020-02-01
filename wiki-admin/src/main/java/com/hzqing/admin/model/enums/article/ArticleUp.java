package com.hzqing.admin.model.enums.article;

import com.baomidou.mybatisplus.core.enums.IEnum;

/**
 * 文章是否置顶
 * @author hzqing
 * @date 2020-02-01 19:32
 */
public enum  ArticleUp implements IEnum<Boolean> {

    /**
     * 置顶
     */
    UP(true,"置顶"),

    /**
     * 不置顶
     */
    NO_UP(false,"不置顶");

    ArticleUp(Boolean value,String desc) {
        this.value = value;
        this.desc = desc;
    }

    private Boolean value;

    private String desc;

    @Override
    public Boolean getValue() {
        return this.value;
    }


    public String getDesc() {
        return desc;
    }
}
