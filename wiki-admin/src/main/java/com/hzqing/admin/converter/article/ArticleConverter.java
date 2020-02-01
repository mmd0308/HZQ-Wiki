package com.hzqing.admin.converter.article;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzqing.admin.model.entity.article.Article;
import com.hzqing.admin.model.vo.article.ArticleVO;
import org.mapstruct.Mapper;

/**
 * 按钮模块实体和dto转换器
 * @author hzqing
 * @date 2019-08-13 21:24
 */
@Mapper(componentModel = "spring")
public interface ArticleConverter {


    Page<ArticleVO> pageArticleToPageArticleVo(Page<Article> articlePages);
}
