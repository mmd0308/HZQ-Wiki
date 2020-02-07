package com.hzqing.admin.converter.article;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzqing.admin.model.dto.article.ArticleDto;
import com.hzqing.admin.model.entity.article.Article;
import com.hzqing.admin.model.vo.article.ArticleDetailVO;
import com.hzqing.admin.model.vo.article.ArticleMinimalVO;
import org.mapstruct.Mapper;

/**
 * 文章管理转换器
 * @author hzqing
 * @date 2019-08-13 21:24
 */
@Mapper(componentModel = "spring")
public interface ArticleConverter {

    Page<ArticleMinimalVO> pageArticleDtoToPageArticleMinimalVo(Page<ArticleDto> articlePage);

    ArticleDetailVO DtoToDetailVO(ArticleDto article);

    Page<ArticleMinimalVO> pageArticleToPageArticleMinimalVo(Page<Article> articlePages);
}
