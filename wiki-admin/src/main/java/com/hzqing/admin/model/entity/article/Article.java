package com.hzqing.admin.model.entity.article;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.annotation.*;
import com.hzqing.admin.domain.base.Base;
import com.hzqing.admin.model.entity.base.BaseEntity;
import com.hzqing.admin.model.enums.article.ArticleState;
import com.hzqing.admin.model.enums.article.ArticleUp;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author hzqing
 * @date 2019-07-01 18:11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("hw_article")
public class Article extends BaseEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String title;

    private String content;

    @TableField("content_html")
    private String contentHtml;

    /**
     * 排序
     */
    private Integer sequence;

    /**
     * 文章摘要
     */
    @TableField("hw_desc")
    private String hwDesc;

    /**
     * 文章状态
     */
    @TableField("hw_state")
    private ArticleState hwState;

    /**
     * 是否置顶
     */
    @TableField("hw_up")
    private ArticleUp hwUp;

    @TableField("user_id")
    private Integer userId;

    @TableField("create_by")
    private Integer createBy;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private LocalDateTime updateTime;
}
