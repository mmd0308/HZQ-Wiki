package com.hzqing.admin.mapper.article;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzqing.admin.model.dto.article.ArticleDto;
import com.hzqing.admin.model.entity.article.Article;
import org.apache.ibatis.annotations.Param;

/**
 * @author hzqing
 * @date 2019-07-1 18:50
 */
public interface ArticleMapper extends BaseMapper<Article> {

    /**
     * 分页查询已经发布的文章，或者根据标签查询
     * @param articlePage
     * @param articledto
     * @return
     */
    IPage<ArticleDto> selectPageByStateOrTag(Page<ArticleDto> articlePage, @Param("articledto") ArticleDto articledto);

    /**
     * 根据id获取数据
     * @param id
     * @return
     */
    ArticleDto selectDtoById(int id);
}
