package com.hzqing.admin.common.result.support;

import com.hzqing.admin.common.result.RestResult;
import lombok.Data;

/**
 * @author hzqing
 * @date 2020-01-15 14:49
 */
@Data
public class PageRestResult<T> extends RestResult<T> {

    /**
     * 分页的总数
     */
    private long total;


    public PageRestResult(String code, String msg,long total, T attributes) {
        super(code, msg, attributes);
        this.total = total;
    }
}
