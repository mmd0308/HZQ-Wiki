package com.hzqing.admin.domain.system;

import lombok.Data;

import java.util.List;

/**
 * @author hzqing
 * @date 2019-05-17 10:47
 */
@Data
public class UserInfo {

    private String name;

    private List<String> roles;

    private List<String> permission;

}
