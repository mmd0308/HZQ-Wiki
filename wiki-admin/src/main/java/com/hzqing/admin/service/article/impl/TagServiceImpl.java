package com.hzqing.admin.service.article.impl;

import com.hzqing.admin.mapper.article.TagMapper;
import com.hzqing.admin.model.entity.article.Article;
import com.hzqing.admin.model.entity.article.ArticleTag;
import com.hzqing.admin.model.entity.article.Tag;
import com.hzqing.admin.service.article.ITagService;
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
public class TagServiceImpl implements ITagService {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public List<Article> getList(Tag tag) {
        return null;
    }

    @Override
    public int removedById(int id) {
        return tagMapper.deleteById(id);
    }

    @Override
    public int create(Tag tag) {
        tagMapper.insert(tag);
        return tag.getId();
    }

    @Override
    public void modifyById(Tag tag) {

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
}
