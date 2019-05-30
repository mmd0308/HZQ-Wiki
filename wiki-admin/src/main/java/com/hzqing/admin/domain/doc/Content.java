package com.hzqing.admin.domain.doc;

import com.hzqing.admin.domain.base.Base;
import lombok.Data;

/**
 * @author hzqing
 * @date 2019-05-30 16:09
 */
@Data
public class Content extends Base {

    private Integer id;

    private Integer docId;

    private Integer cataId;

    private String version;

    /**
     * 文档内容 mk格式
     */
    private String content;

    /**
     * 文档内容 html格式
     */
    private String cotentHtml;

}
