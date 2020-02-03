package com.hzqing.admin.model.vo.article;

import lombok.Data;
import lombok.ToString;

/**
 * @author hzqing
 * @date 2020-02-03 09:25
 */
@Data
@ToString
public class ArticleDetailVO extends ArticleMinimalVO{

    private String content;

    private String contentHtml;

}
