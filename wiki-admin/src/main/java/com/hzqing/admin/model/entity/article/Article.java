package com.hzqing.admin.model.entity.article;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hzqing.admin.domain.base.Base;
import com.hzqing.admin.model.entity.base.BaseEntity;
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

    @TableField("hw_desc")
    private String hwDesc;

    /**
     * 状态 1 草稿 2 发布
     */
    @TableField("hw_state")
    private Integer hwState;

    /**
     * 是否置顶 0 否 1是
     */
    @TableField("hw_up")
    private Integer hwUp;

    @TableField("user_id")
    private Integer userId;

    @TableField("create_by")
    private Integer createBy;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private LocalDateTime updateTime;
}
