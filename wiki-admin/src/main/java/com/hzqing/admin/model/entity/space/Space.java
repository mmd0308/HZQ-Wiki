package com.hzqing.admin.model.entity.space;

import com.hzqing.admin.model.entity.base.BaseEntity;
import lombok.Data;

/**
 * @author hzqing
 * @date 2019-05-20 08:26
 */
@Data
public class Space extends BaseEntity {

    private Integer id;

    private String name;

    private String remark;

    private Integer visitLevel;

}
