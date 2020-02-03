package com.hzqing.admin.service.doc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hzqing.admin.mapper.doc.ContentMapper;
import com.hzqing.admin.model.dto.doc.ContentDto;
import com.hzqing.admin.model.entity.doc.Content;
import com.hzqing.admin.service.doc.IContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hzqing
 * @date 2019-05-30 09:45
 */
@Service
public class ContentServiceImpl implements IContentService {
    @Autowired
    private ContentMapper contentMapper;

    @Override
    public List<Content> selectList(Content content) {
       return null;
    }

    @Override
    public int insert(Content content) {
        contentMapper.insert(content);
        // 返回主键
        return content.getId();
    }

    @Override
    public int update(Content content) {
        return contentMapper.updateById(content);
    }

    @Override
    public int deletedById(Integer id) {
        return contentMapper.deleteById(id);
    }

    @Override
    public Content get(int id) {
        return contentMapper.selectById(id);
    }

    @Override
    public List<ContentDto> getListAllByDocId(Integer docId) {
        return contentMapper.selectListByDocId(docId);
    }

}
