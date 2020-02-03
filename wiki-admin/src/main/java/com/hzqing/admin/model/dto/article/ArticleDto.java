package com.hzqing.admin.model.dto.article;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.hzqing.admin.model.enums.article.ArticleState;
import com.hzqing.admin.model.enums.article.ArticleUp;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author hzqing
 * @date 2020-01-17 16:24
 */
@Data
@ToString
public class ArticleDto {

    /**
     * 标签Id
     */
    private Integer tagId;

    private Integer id;

    private String title;

    private String content;

    private String contentHtml;

    private Integer sequence;

    private String hwDesc;

    private ArticleState hwState;

    private ArticleUp hwUp;

    private Integer userId;

    private Integer createBy;

    /**
     * 创建人名称
     */
    private String createName;


    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
