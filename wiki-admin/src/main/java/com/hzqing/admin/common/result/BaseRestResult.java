package com.hzqing.admin.common.result;

import lombok.Data;

import java.io.Serializable;

/**
 * @author hzqing
 * @date 2020-01-15 14:52
 */
@Data
public abstract class BaseRestResult implements Serializable {
    /**
     * 请求响应码
     */
    private String code;

    /**
     * 响应吗对应的提示信息
     */
    private String msg;

    public BaseRestResult(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
