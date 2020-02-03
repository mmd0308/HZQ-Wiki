package com.hzqing.admin.model.entity.doc;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hzqing.admin.model.entity.base.SimpleBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author hzqing
 * @date 2019-05-30 09:42
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("hw_doc_content")
public class Content extends SimpleBaseEntity {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @TableField("doc_id")
    private Integer docId;

    private String  title;

    @TableField("parent_id")
    private Integer parentId;

    private String version;

    /**
     * 排序
     */
    private Integer sequence;

    /**
     * 文档内容 mk格式
     */
    private String content;

    /**
     * 文档内容 html格式
     */
    @TableField("content_html")
    private String contentHtml;

    /**
     * 文档类型: A 文章(默认) D 目录 H 链接
     */
    @TableField("doc_type")
    private String docType;


}
