package com.hzqing.admin.model.entity.system;

import com.hzqing.admin.model.entity.base.BaseEntity;
import lombok.Data;

/**
 * @author hzqing
 * @date 2019-05-17 09:32
 */
@Data
public class User extends BaseEntity {

    private int id;

    private String name;

    private String username;

    private String password;

    private String email;

    private String phone;

    /**
     * 用户头像
     */
    private String img;


    /**
     * 备注说明
     */
    private String remark;

}
