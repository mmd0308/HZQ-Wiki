package com.hzqing.admin.domain.system;

import lombok.Data;

import java.util.List;

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
