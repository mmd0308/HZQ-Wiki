package com.hzqing.admin.service.article.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzqing.admin.mapper.article.TagMapper;
import com.hzqing.admin.model.entity.article.Article;
import com.hzqing.admin.model.entity.article.ArticleTag;
import com.hzqing.admin.model.entity.article.Tag;
import com.hzqing.admin.service.article.IArticleTagService;
import com.hzqing.admin.service.article.ITagService;
import lombok.extern.slf4j.Slf4j;
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
public class TagServiceImpl implements ITagService {

    @Autowired
    @SuppressWarnings("all")
    private TagMapper tagMapper;

    @Autowired
    private IArticleTagService articleTagService;

    @Override
    public List<Article> getList(Tag tag) {
        return null;
    }

    @Transactional
    @Override
    public int removedById(int id) {
        // 根据id删除标签
        int res =  tagMapper.deleteById(id);
        log.info("TagServiceImpl.removedById 删除该标签关联文章的所有关系数据");
        articleTagService.removeByTagId(id);
        return res;
    }

    @Override
    public int create(Tag tag) {
        tagMapper.insert(tag);
        return tag.getId();
    }

    @Override
    public void modifyById(Tag tag) {
        tagMapper.updateById(tag);
    }

    @Override
    public List<Tag> getListAll() {
        return tagMapper.selectList(null);
    }


    @Override
    public void createForBatchByNames(List<String> tagNames) {
        List<Tag> articleTags = new ArrayList<>(tagNames.size());
        tagNames.forEach(name -> {
            articleTags.add(new Tag(name));
        });
        tagMapper.insertForBatch(articleTags);
    }

    @Override
    public List<Tag> getListByNames(List<String> tagNames) {
        return tagMapper.selectByNames(tagNames);
    }

    @Override
    public Page<Tag> getPage(int num, int size, Tag tag) {
        return (Page<Tag>) tagMapper.selectPage(new Page<>(num,size),new QueryWrapper<>(tag));
    }
}
