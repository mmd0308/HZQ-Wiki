package com.hzqing.admin.domain.article;

import com.hzqing.admin.domain.base.Base;
import lombok.Data;

/**
 * @author hzqing
 * @date 2019-07-01 18:11
 */
@Data
public class Article extends Base {

    private Integer id;

    private String title;

    private String content;

    private String contentHtml;

    /**
     * 排序
     */
    private Integer sequence;

    private String hwDesc;

    /**
     * 状态 1 草稿 2 发布
     */
    private Integer hwState;

    /**
     * 是否置顶 0 否 1是
     */
    private Integer hwUp;

    private Integer userId;
}
