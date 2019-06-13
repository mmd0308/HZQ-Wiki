package com.hzqing.admin.dto.doc;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author hzqing
 * @date 2019-06-13 15:12
 */
@Data
public class MemberDto {
    private Integer id;

    private Integer userId;

    private Integer docId;

    private Integer spaceId;

    /**
     * 空间成员操作权限   0 拥有者 1 浏览者 2 编辑者 3 管理员
     */
    private Integer privilege;

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
