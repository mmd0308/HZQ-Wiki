package com.hzqing.admin.model.entity.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hzqing.admin.domain.base.Base;
import com.hzqing.admin.model.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author hzqing
 * @date 2019-06-29 11:43
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("hw_login_log")
public class LoginLog extends BaseEntity {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private String username;

    private String ip;

    private String browser;

    private String system;

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

}
