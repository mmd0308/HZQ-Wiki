package com.hzqing.admin.model.dto.space;

import com.hzqing.admin.model.enums.space.UserSpacePrivilege;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author hzqing
 * @date 2019-06-05 13:24
 */
@Data
public class UserSpaceDto  {


    private Integer id;

    private Integer userId;

    private Integer spaceId;

    private UserSpacePrivilege privilege;

    private Integer createBy;

    private LocalDateTime createTime;

    /**
     * 到期时间
     */
    private LocalDate expireTime;

    /**
     * 用户名称
     */
    private String fullName;
}
