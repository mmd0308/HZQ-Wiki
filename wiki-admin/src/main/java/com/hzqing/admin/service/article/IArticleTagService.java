package com.hzqing.admin.service.article;

import com.hzqing.admin.model.entity.article.ArticleTag;

import java.util.List;

/**
 * @author hzqing
 * @date 2019-07-1 18:53
 */
public interface IArticleTagService {

    /**
     * 根据文章id删除数据
     * @param id
     */
    void removeByArticleId(Integer id);


    /**
     * 批量创建关联数据
     * @param articleTags
     */
    void createForBatch(List<ArticleTag> articleTags);

    /**
     * 根据标签id，删除所有的数据
     * @param id
     */
    void removeByTagId(int id);
}
