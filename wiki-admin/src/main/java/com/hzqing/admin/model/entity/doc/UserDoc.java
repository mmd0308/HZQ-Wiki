package com.hzqing.admin.model.entity.doc;

import com.hzqing.admin.model.entity.base.BaseEntity;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 用户跟文档的关系实体类
 * @author hzqing
 * @date 2019-05-21 14:28
 */
@Data
public class UserDoc extends BaseEntity {

    private int id;

    private int userId;

    private int docId;

    /**
     * 空间成员操作权限   0 拥有者 1 浏览者 2 编辑者 3 管理员
     */
    private int privilege;

    private int createBy;

    private LocalDateTime createTime;

    /**
     * 到期时间
     */
    private LocalDate expireTime;

}
