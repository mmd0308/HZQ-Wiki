package com.hzqing.admin.mapper.article;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hzqing.admin.model.entity.article.ArticleTag;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hzqing
 * @date 2019-07-1 18:50
 */
@Repository
public interface ArticleTagMapper extends BaseMapper<ArticleTag> {

    /**
     * 批量创建
     * @param articleTags
     */
    void insertForBatch(@Param("articleTags")List<ArticleTag> articleTags);
}
