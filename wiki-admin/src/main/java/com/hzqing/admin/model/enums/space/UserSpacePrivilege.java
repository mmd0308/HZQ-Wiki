package com.hzqing.admin.model.enums.space;

import com.baomidou.mybatisplus.core.enums.IEnum;

/**
 * 用户空间操作权限
 * @author hzqing
 * @date 2020-02-02 12:57
 */
public enum UserSpacePrivilege implements IEnum<Integer> {

    OWNER(1,"拥有者"),

    VISITORS(2,"浏览者"),

    EDITORS(3,"编辑者"),

    ADMINISTRATOR(4,"管理员");


    private Integer value;

    private String desc;

    UserSpacePrivilege(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }
}
