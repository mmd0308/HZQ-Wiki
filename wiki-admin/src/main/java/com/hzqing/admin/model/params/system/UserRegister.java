package com.hzqing.admin.model.params.system;

import lombok.Data;

/**
 * 用户注册
 * @author hzqing
 * @date 2020-02-03 13:37
 */
@Data
public class UserRegister {

    private String name;

    private String username;

    private String password;

    private String email;
}
