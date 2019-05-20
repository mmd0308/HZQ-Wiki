package com.hzqing.admin.domain.base;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author hzqing
 * @date 2019-05-20 08:28
 */
@Data
public class Base implements Serializable {

    private int createBy;

    private LocalDateTime createTime;

    private int updateBy;

    private LocalDateTime updateTime;
}
