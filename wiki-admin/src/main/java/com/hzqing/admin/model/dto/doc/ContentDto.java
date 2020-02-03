package com.hzqing.admin.model.dto.doc;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author hzqing
 * @date 2019-05-30 09:42
 */
@Data
public class ContentDto {

    private Integer id;

    private Integer docId;

    private String  title;

    private Integer parentId;

    private String version;

    private Integer sequence;

    private String content;

    private String contentHtml;

    private String docType;

    private Boolean parent;

    private Integer createBy;

    private String createName;

    private LocalDateTime createTime;

    private Integer updateBy;

    private LocalDateTime updateTime;

}
