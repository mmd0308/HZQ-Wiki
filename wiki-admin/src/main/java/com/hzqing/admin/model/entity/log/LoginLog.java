package com.hzqing.admin.model.entity.log;

import com.hzqing.admin.model.entity.base.BaseEntity;
import lombok.Data;

/**
 * @author hzqing
 * @date 2019-06-29 11:43
 */
@Data
public class LoginLog extends BaseEntity {

    private int id;

    private String username;

    private String ip;

    private String browser;

    private String system;

}
