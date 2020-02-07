package com.hzqing.admin.model.vo.article;

import com.hzqing.admin.model.enums.article.ArticleState;
import com.hzqing.admin.model.enums.article.ArticleUp;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 最少参数的文章信息
 * @author hzqing
 * @date 2020-02-03 08:50
 */
@Data
@ToString
public class ArticleMinimalVO implements Serializable {

    private Integer id;

    private String title;

    private String hwDesc;

    private ArticleState hwState;

    private ArticleUp hwUp;

    private Integer userId;

    private Integer createBy;

    private String createName;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
