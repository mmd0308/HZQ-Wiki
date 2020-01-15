package com.hzqing.admin.model.entity.config;

import com.hzqing.admin.model.entity.base.BaseEntity;
import lombok.Data;

/**
 * @author hzqing
 * @date 2019-06-11 14:09
 */
@Data
public class Config  extends BaseEntity {

    private  Integer id;

    /**
     * 键 唯一
     */
    private String key;

    private String value;

}
