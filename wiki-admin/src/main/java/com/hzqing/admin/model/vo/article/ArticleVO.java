package com.hzqing.admin.model.vo.article;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author hzqing
 * @date 2020-02-01 17:05
 */
@Data
public class ArticleVO implements Serializable {

    private Integer id;

    private String title;

    private String content;

    private String contentHtml;

    private Integer sequence;

    private String hwDesc;

    /**
     * 状态 1 草稿 2 发布
     */
    private String hwState;

    /**
     * 是否置顶 0 否 1是
     */
    private String hwUp;

    private Integer userId;

    private Integer createBy;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
