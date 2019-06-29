package com.hzqing.admin.domain.log;

import com.hzqing.admin.domain.base.Base;
import lombok.Data;

/**
 * @author hzqing
 * @date 2019-06-29 11:43
 */
@Data
public class LoginLog extends Base {

    private int id;

    private String username;

    private String ip;

    private String browser;

    private String system;

}
