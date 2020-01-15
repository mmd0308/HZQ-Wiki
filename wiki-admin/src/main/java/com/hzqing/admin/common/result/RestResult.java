package com.hzqing.admin.common.result;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * 结果响应
 * @author hzq
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestResult<T> extends BaseRestResult {
    private static final long serialVersionUID = -450599376115985279L;

    /**
     * 返回的数据
     */
    private T data;


    public RestResult(String code, String msg, T attributes) {
        super(code,msg);
        this.data = attributes;
    }


}
