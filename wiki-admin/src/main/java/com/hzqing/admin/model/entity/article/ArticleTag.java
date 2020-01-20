package com.hzqing.admin.model.entity.article;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author hzqing
 * @date 2020-01-17 14:49
 */
@Data
@Accessors(chain = true)
@TableName("hw_article_tag")
public class ArticleTag {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @TableField("article_id")
    private Integer articleId;

    @TableField("tag_id")
    private Integer tagId;

    public ArticleTag() {
    }

    public ArticleTag(Integer articleId, Integer tagId) {
        this.articleId = articleId;
        this.tagId = tagId;
    }
}
