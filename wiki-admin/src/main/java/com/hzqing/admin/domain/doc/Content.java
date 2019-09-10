package com.hzqing.admin.domain.doc;

import com.hzqing.admin.domain.base.Base;
import lombok.Data;

/**
 * @author hzqing
 * @date 2019-05-30 09:42
 */
@Data
public class Content extends Base {

    private Integer id;

    private Integer docId;

    /**
     * 标题
     */
    private String  title;

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
    private String contentHtml;

    /**
     * 文档类型: A 文章(默认) D 目录 H 链接
     */
    private String docType;

    /**
     * 是否为目录
     */
    private boolean parent;

}
