package com.hzqing.admin.domain.doc;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户跟文档的关系实体类
 * @author hzqing
 * @date 2019-05-21 14:28
 */
@Data
public class UserDoc {

    private int id;

    private int userId;

    private int docId;

    /**
     * 文档成员操作权限  1 浏览者 2 编辑者 3 管理员
     */
    private int privilege;

    private int createBy;

    private LocalDateTime createTime;

}
