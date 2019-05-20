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
    /**
     * 创建人Id
     */
    private int createBy;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新人id
     */
    private int updateBy;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
