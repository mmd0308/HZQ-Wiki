package com.hzqing.admin.model.entity.base;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 该实体类包括创建时间，更新时间，创建人id及更新人id
 * @author hzqing
 * @date 2020-02-02 08:28
 */
@Data
public class SimpleBaseEntity extends BaseEntity{
    /**
     * 创建人Id
     */
    @TableField("create_by")
    private Integer createBy;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 更新人id
     */
    @TableField("update_by")
    private Integer updateBy;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;
}
