package com.hzqing.admin.model.dto.space;

import com.baomidou.mybatisplus.annotation.TableField;
import com.hzqing.admin.model.enums.space.SpaceVisitLevel;
import com.hzqing.admin.model.enums.space.UserSpacePrivilege;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author hzqing
 * @date 2020-02-05 11:16
 */
@Data
@ToString
public class SpaceDto {

    private Integer id;

    private String name;

    private String remark;

    private SpaceVisitLevel visitLevel;

    private Integer createBy;

    private LocalDateTime createTime;

    private Integer updateBy;

    private LocalDateTime updateTime;

    /**
     * 当前访问需要获取空间的用户id
     */
    private Integer userId;

    /**
     * 创建人
     */
    private String createName;

    /**
     * 用户和该空间的关系
     */
    private UserSpacePrivilege userSpacePrivilege;
}
