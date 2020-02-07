package com.hzqing.admin.service.article.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzqing.admin.common.constants.Constant;
import com.hzqing.admin.common.utils.ReplaceStrUtil;
import com.hzqing.admin.common.utils.UserAuthUtils;
import com.hzqing.admin.mapper.article.ArticleMapper;
import com.hzqing.admin.model.dto.article.ArticleDto;
import com.hzqing.admin.model.dto.system.UserInfo;
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

import java.time.LocalDateTime;
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
        Article query = new Article();
        query.setUserId(article.getUserId());
        final QueryWrapper<Article> queryWrapper = new QueryWrapper<>(query);
        if (StringUtils.isNotEmpty(article.getTitle())){
            queryWrapper.like("title",article.getTitle());
        }
        return (Page<Article>) articleMapper.selectPage(new Page<>(num,size),queryWrapper);
    }

    @Override
    public int create(Article article) {
        article.setHwState(ArticleState.DRAFT);
        final UserInfo userInfo = UserAuthUtils.getUserInfo();
        article.setUserId(userInfo.getId());
        article.setCreateBy(userInfo.getId());
        article.setCreateTime(LocalDateTime.now());
        articleMapper.insert(article);
        return article.getId();
    }

    @Override
    public void modifyById(Article article) {
        // 如果文章被修改，默认存储到草稿箱
        article.setHwState(ArticleState.DRAFT);
        article.setUpdateTime(LocalDateTime.now());
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
    public Page<ArticleDto> getPageByStateOrTag(int num, int size, ArticleDto article) {
        IPage<ArticleDto> articleIPage = articleMapper.selectPageByStateOrTag(new Page<ArticleDto>(num, size), article);
        return (Page<ArticleDto>) articleIPage;
    }

    @Override
    public ArticleDto getDtoById(int id) {
        return articleMapper.selectDtoById(id);
    }
}
