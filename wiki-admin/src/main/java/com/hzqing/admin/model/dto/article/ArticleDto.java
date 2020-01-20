package com.hzqing.admin.model.dto.article;

import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * @author hzqing
 * @date 2020-01-17 16:24
 */
@Data
@Alias("articledto")
public class ArticleDto {

    /**
     * 标签Id
     */
    private Integer tagId;


    /**
     * 状态 1 草稿 2 发布
     */
    private Integer hwState;
}
