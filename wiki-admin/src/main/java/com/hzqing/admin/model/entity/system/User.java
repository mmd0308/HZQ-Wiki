package com.hzqing.admin.model.entity.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hzqing.admin.model.entity.base.BaseEntity;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author hzqing
 * @date 2019-05-17 09:32
 */
@Data
@TableName(value = "hw_user")
public class User extends BaseEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private String username;

    private String password;

    private String email;

    private String phone;

    /**
     * 用户头像
     */
    private String img;

    /**
     * 备注说明
     */
    private String remark;


    @TableField("create_by")
    private Integer createBy;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_by")
    private Integer updateBy;

    @TableField("update_time")
    private LocalDateTime updateTime;

}
