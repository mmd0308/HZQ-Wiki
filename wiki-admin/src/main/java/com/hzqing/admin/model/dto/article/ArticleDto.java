package com.hzqing.admin.model.dto.article;

import com.hzqing.admin.model.enums.article.ArticleState;
import lombok.Data;

/**
 * @author hzqing
 * @date 2020-01-17 16:24
 */
@Data
public class ArticleDto {

    /**
     * 标签Id
     */
    private Integer tagId;


    /**
     * 状态
     */
    private ArticleState hwState;
}
