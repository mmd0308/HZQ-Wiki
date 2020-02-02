package com.hzqing.admin.common.exception.support;

import com.hzqing.admin.common.exception.BaseException;

/**
 * @author hzqing
 * @date 2020-01-15 14:34
 */
public class ParamsValidateException extends BaseException {

    public ParamsValidateException() {
        super(null,null);
    }

    public ParamsValidateException(String code, String msg) {
        super(code, msg);
    }
}

