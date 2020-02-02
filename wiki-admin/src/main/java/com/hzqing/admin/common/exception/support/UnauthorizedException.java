package com.hzqing.admin.common.exception.support;

import com.hzqing.admin.common.constants.RestResultCodeConstants;
import com.hzqing.admin.common.exception.BaseException;

/**
 * 权限不足异常
 * @author hzqing
 * @date 2020-02-02 17:45
 */
public class UnauthorizedException extends BaseException {

    public UnauthorizedException() {
        super(RestResultCodeConstants.UNAUTHORIZED.getCode(),
                RestResultCodeConstants.UNAUTHORIZED.getMsg());
    }

    public UnauthorizedException(String code, String msg) {
        super(code, msg);
    }
}
