package com.hzqing.admin.service.article.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hzqing.admin.mapper.article.ArticleTagMapper;
import com.hzqing.admin.model.entity.article.ArticleTag;
import com.hzqing.admin.service.article.IArticleTagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hzqing
 * @date 2019-07-1 18:54
 */
@Slf4j
@Service
public class ArticleTagServiceImpl implements IArticleTagService {

    @Autowired
    private ArticleTagMapper articleTagMapper;

    @Override
    public void removeByArticleId(Integer id) {
        ArticleTag articleTag = new ArticleTag();
        articleTag.setArticleId(id);
        articleTagMapper.delete(new QueryWrapper<>(articleTag));
    }

    @Override
    public void createForBatch(List<ArticleTag> articleTags) {
        articleTagMapper.insertForBatch(articleTags);
    }


}
