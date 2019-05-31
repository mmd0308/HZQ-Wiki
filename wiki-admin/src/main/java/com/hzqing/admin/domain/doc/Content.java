package com.hzqing.admin.domain.doc;

import com.hzqing.admin.domain.base.Base;
import com.hzqing.admin.domain.base.TreeNode;
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

}
