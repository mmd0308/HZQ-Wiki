package com.hzqing.admin.domain.system;

import com.hzqing.admin.domain.base.Base;
import lombok.Data;

/**
 * @author hzqing
 * @date 2019-05-17 09:32
 */
@Data
public class User extends Base {

    private int id;

    private String name;

    private String username;

    private String password;

    private String email;

    private String phone;

    /**
     * 备注说明
     */
    private String remark;

}
