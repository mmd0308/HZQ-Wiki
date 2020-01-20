package com.hzqing.admin.model.dto.system;

import lombok.Data;

/**
 * @author hzqing
 * @date 2019-05-17 10:47
 */
@Data
public class UserInfo {

    private int id;

    private String name;

    private String username;

    private String password;

    private String[] resources;

    private String[] roles;

    private String token;

    /**
     * 用户头像
     */
    private String img;

}
