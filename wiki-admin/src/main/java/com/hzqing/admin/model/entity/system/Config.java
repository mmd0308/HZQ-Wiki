package com.hzqing.admin.model.entity.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hzqing.admin.model.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author hzqing
 * @date 2019-06-11 14:09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("hw_login_log")
public class Config extends BaseEntity {

    @TableId(value = "id",type = IdType.AUTO)
    private  Integer id;

    /**
     * 键 唯一
     */
    private String key;

    private String value;

    @TableField("update_by")
    private Integer updateBy;

    @TableField("update_time")
    private LocalDateTime updateTime;

}
