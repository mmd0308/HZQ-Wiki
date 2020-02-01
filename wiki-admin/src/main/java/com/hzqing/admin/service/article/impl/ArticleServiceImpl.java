package com.hzqing.admin.service.article.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzqing.admin.common.constants.Constant;
import com.hzqing.admin.common.utils.ReplaceStrUtil;
import com.hzqing.admin.mapper.article.ArticleMapper;
import com.hzqing.admin.model.dto.article.ArticleDto;
import com.hzqing.admin.model.entity.article.Article;
import com.hzqing.admin.model.entity.article.ArticleTag;
import com.hzqing.admin.model.enums.article.ArticleState;
import com.hzqing.admin.model.params.ArticleRelease;
import com.hzqing.admin.service.article.IArticleService;
import com.hzqing.admin.service.article.IArticleTagService;
import com.hzqing.admin.service.article.ITagService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    private IArticleTagService articleTagService;

    @Autowired
    private ITagService tagService;

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
        return (Page<Article>) articleMapper.selectPage(new Page<>(num,size),new QueryWrapper<>(article));
    }

    @Override
    public int create(Article article) {
        articleMapper.insert(article);
        return article.getId();
    }

    @Override
    public void modifyById(Article article) {

        articleMapper.updateById(article);
    }


    @Override
    @Transactional // 开启事务
    public void createRelease(ArticleRelease articleRelease) {
        // 删除该文章已经关联的标签
        articleTagService.removeByArticleId(articleRelease.getId());

        // 将标签跟文章进行关联
        List<ArticleTag> articleTags = new ArrayList<>(articleRelease.getTagIds().size());
        articleRelease.getTagIds().forEach(id -> {
            articleTags.add(new ArticleTag(articleRelease.getId(),id));
        });
        articleTagService.createForBatch(articleTags);

        // 发表文章
        Article article = articleMapper.selectById(articleRelease.getId());
        article.setHwState(ArticleState.RELEASE);

        if (StringUtils.isNotEmpty(articleRelease.getHwDesc())){
            article.setHwDesc(articleRelease.getHwDesc());
        }else {
            // 如果摘要为空，自动生成摘要
            if (StringUtils.isNotEmpty(article.getContentHtml())){
                String desc = ReplaceStrUtil.delHtmlTag(article.getContentHtml());
                article.setHwDesc(desc.length() > Constant.ARTICLE_DESC_LENGHT ? desc.substring(0, Constant.ARTICLE_DESC_LENGHT ) : desc);
            }
        }
        // 更新文章
        articleMapper.updateById(article);
    }

    @Override
    public Page<Article> getPageByStateOrTag(int num, int size, ArticleDto article) {
        IPage<Article> articleIPage = articleMapper.selectPageByStateOrTag(new Page<Article>(num, size), article);
        return (Page<Article>) articleIPage;
    }
}
