package com.hzqing.admin.service.article.impl;

import com.hzqing.admin.common.utils.ReplaceStrUtil;
import com.hzqing.admin.domain.article.Article;
import com.hzqing.admin.mapper.article.ArticleMapper;
import com.hzqing.admin.service.article.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hzqing
 * @date 2019-07-1 18:54
 */
@Service
public class ArticleServiceImpl implements IArticleService {

    @Autowired
    private ArticleMapper articleMapper;


    @Override
    public List<Article> selectList(Article article) {
        return articleMapper.selectList(article);
    }

    @Override
    public int insert(Article article) {
        articleMapper.insert(article);
        return article.getId();
    }

    @Override
    public int update(Article article) {
        if (null != article.getContentHtml() && article.getContentHtml() != "") {
            String desc = ReplaceStrUtil.delHtmlTag(article.getContentHtml());
            article.setHwDesc(desc.length() > 150 ? desc.substring(0, 150) : desc);
        }
        return articleMapper.update(article);
    }



    @Override
    public Article get(int id) {
        return articleMapper.get(id);
    }

    @Override
    public int deletedById(String id) {
        return articleMapper.deletedById(id);
    }


}
