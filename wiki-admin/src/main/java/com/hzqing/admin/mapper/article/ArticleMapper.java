package com.hzqing.admin.mapper.article;

import com.hzqing.admin.domain.article.Article;

import java.util.List;

/**
 * @author hzqing
 * @date 2019-07-1 18:50
 */
public interface ArticleMapper {

    List<Article> selectList(Article doc);

    int update(Article doc);

    int insert(Article doc);

    Article get(int id);

    int deletedById(String id);

}
