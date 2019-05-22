package com.hzqing.admin.domain.doc;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hzqing.admin.domain.base.Base;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author hzqing
 * @date 2019-05-17 09:53
 */
@Data
public class Doc extends Base {

    private Integer id;

    private String name;

    private Integer spaceId;

    private String remark;

    private Integer visitLevel;

    private String img;



}
