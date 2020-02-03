package com.hzqing.admin.model.vo.doc;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 最少参数的文档目录信息
 * @author hzqing
 * @date 2020-02-03 08:50
 */
@Data
@ToString
public class ContentMinimalVO implements Serializable {

    private Integer id;

    private Integer docId;
    /**
     * 标题
     */
    private String  title;

    private Integer parentId;

    /**
     * 排序
     */
    private Integer sequence;

    /**
     * 文档类型: A 文章(默认) D 目录 H 链接
     */
    private String docType;

    /**
     * 是否为目录
     */
    private boolean parent;
}
