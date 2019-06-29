package com.hzqing.admin.domain.config;

import com.hzqing.admin.domain.base.Base;
import lombok.Data;

/**
 * @author hzqing
 * @date 2019-06-11 14:09
 */
@Data
public class Config  extends Base {

    private  Integer id;

    /**
     * 键 唯一
     */
    private String key;

    private String value;

}
