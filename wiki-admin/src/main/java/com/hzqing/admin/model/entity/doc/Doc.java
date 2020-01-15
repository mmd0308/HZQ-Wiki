package com.hzqing.admin.model.entity.doc;

import com.hzqing.admin.model.entity.base.BaseEntity;
import lombok.Data;

/**
 * @author hzqing
 * @date 2019-05-17 09:53
 */
@Data
public class Doc extends BaseEntity {

    private Integer id;

    private String name;

    private Integer spaceId;

    private String remark;

    private Integer visitLevel;

    private String img;



}
