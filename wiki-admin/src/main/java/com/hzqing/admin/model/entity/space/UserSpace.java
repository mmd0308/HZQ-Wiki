package com.hzqing.admin.model.entity.space;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hzqing.admin.model.entity.base.BaseEntity;
import com.hzqing.admin.model.enums.space.UserSpacePrivilege;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 用户跟空间的关系实体类
 * @author hzqing
 * @date 2019-05-21 14:28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("hw_user_space")
public class UserSpace extends BaseEntity {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @TableField("user_id")
    private Integer userId;

    @TableField("space_id")
    private Integer spaceId;

    private UserSpacePrivilege privilege;

    @TableField("create_by")
    private Integer createBy;

    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 到期时间
     */
    @TableField("expire_time")
    private LocalDate expireTime;

}
