package com.hzqing.admin.common.exception;

import lombok.Data;

/**
 * 基础异常类
 * @author hzqing
 * @date 2019-08-10 18:47
 */
@Data
public class BaseException extends RuntimeException{

    /**
     * 异常状态码
     */
    private String code;

    /**
     * 错误提示信息
     */
    private String msg;

    public BaseException(String code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }
}
