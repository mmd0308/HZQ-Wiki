package com.hzqing.admin.model.dto.doc;

import com.hzqing.admin.model.enums.doc.DocVisitLevel;
import com.hzqing.admin.model.enums.doc.UserDocPrivilege;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author hzqing
 * @date 2020-02-03 15:25
 */
@Data
@ToString
public class DocDto {

    private Integer id;

    private String name;

    private Integer spaceId;

    private String remark;

    private DocVisitLevel visitLevel;

    private String img;

    private Integer createBy;

    private String createName;

    private LocalDateTime createTime;

    private Integer updateBy;

    private LocalDateTime updateTime;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 当前用户对该文档的权限
     */
    private UserDocPrivilege userDocPrivilege;

}
