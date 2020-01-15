package com.hzqing.admin.service.article.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzqing.admin.common.utils.ReplaceStrUtil;
import com.hzqing.admin.converter.ArticleConverter;
import com.hzqing.admin.mapper.article.ArticleMapper;
import com.hzqing.admin.model.entity.article.Article;
import com.hzqing.admin.service.article.IArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hzqing
 * @date 2019-07-1 18:54
 */
@Slf4j
@Service
public class ArticleServiceImpl implements IArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ArticleConverter articleConverter;


    @Override
    public List<Article> getList(Article article) {
        return articleMapper.selectList(new QueryWrapper<>(article));
    }

    @Override
    public Article getById(int id) {
        return articleMapper.selectById(id);
    }

    @Override
    public int removedById(int id) {
        return articleMapper.deleteById(id);
    }


    @Override
    public Page<Article> getPage(int num, int size, Article article) {
        IPage<Article> articleIPage = articleMapper.selectPage(new Page<Article>(num, size), new QueryWrapper<>(article));
        return (Page<Article>) articleIPage;
    }

    @Override
    public int create(Article article) {
        return articleMapper.insert(article);
    }

    @Override
    public void modifyById(Article article) {
        if (article.getContent() ==null){
            throw new RuntimeException("...");
        }
        // 题目文章描述
        if (null != article.getContentHtml() && !article.getContentHtml().equals("")) {
            String desc = ReplaceStrUtil.delHtmlTag(article.getContentHtml());
            article.setHwDesc(desc.length() > 150 ? desc.substring(0, 150) : desc);
        }
        articleMapper.updateById(article);
    }

}
