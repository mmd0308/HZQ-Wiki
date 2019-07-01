package com.hzqing.admin.service.article;

import com.hzqing.admin.domain.article.Article;

import java.util.List;

/**
 * @author hzqing
 * @date 2019-07-1 18:53
 */
public interface IArticleService {
    /**
     * 查询所有
     * @param article
     * @return
     */
    List<Article> selectList(Article article);

    /**
     * 新增
     * @param article
     * @return
     */
    int insert(Article article);

    /**
     * 更新
     * @param article
     * @return
     */
    int update(Article article);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Article get(int id);

    /**
     * 根据id删除
     * @param id
     * @return
     */
    int deletedById(String id);


}
