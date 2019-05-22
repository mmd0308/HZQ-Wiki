package com.hzqing.admin.service.doc.impl;

import com.hzqing.admin.domain.doc.Doc;
import com.hzqing.admin.dto.doc.DocDto;
import com.hzqing.admin.mapper.doc.DocMapper;
import com.hzqing.admin.service.doc.IDocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hzqing
 * @date 2019-05-17 09:54
 */
@Service
public class DocServiceImpl implements IDocService {
    @Autowired
    private DocMapper docMapper;

    @Override
    public List<Doc> selectList(Doc doc) {
        return docMapper.selectList(doc);
    }

    @Override
    public int insert(Doc doc) {
        return docMapper.insert(doc);
    }

    @Override
    public int update(Doc doc) {
        return docMapper.update(doc);
    }

    @Override
    public List<DocDto> selectListByID(Doc doc) {
        return docMapper.selectListByID(doc);
    }
}
