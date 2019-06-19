package com.hzqing.admin.service.doc.impl;

import com.hzqing.admin.domain.doc.Doc;
import com.hzqing.admin.domain.doc.UserDoc;
import com.hzqing.admin.dto.doc.DocDto;
import com.hzqing.admin.dto.doc.MemberDto;
import com.hzqing.admin.mapper.doc.DocMapper;
import com.hzqing.admin.mapper.doc.UserDocMapper;
import com.hzqing.admin.service.doc.IDocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author hzqing
 * @date 2019-05-17 09:54
 */
@Service
public class DocServiceImpl implements IDocService {
    @Autowired
    private DocMapper docMapper;

    @Autowired
    private UserDocMapper userDocMapper;


    @Override
    public List<Doc> selectList(Doc doc) {
        return docMapper.selectList(doc);
    }

    @Override
    public int insert(Doc doc) {
        docMapper.insert(doc);
        UserDoc userDoc = new UserDoc();
        userDoc.setDocId(doc.getId());
        userDoc.setPrivilege(0);
        userDoc.setUserId(doc.getCreateBy());
        userDoc.setCreateBy(doc.getCreateBy());
        userDoc.setCreateTime(LocalDateTime.now());
        userDocMapper.insert(userDoc);
        return  doc.getId();
    }

    @Override
    public int update(Doc doc) {
        return docMapper.update(doc);
    }

    @Override
    public List<DocDto> selectListByID(Doc doc) {
        return docMapper.selectListByID(doc);
    }

    @Override
    public List<DocDto> selectListByUserIdAndVL(Doc doc) {
        return docMapper.selectListByUserIdAndVL(doc);
    }

    @Override
    public DocDto get(int id) {
        return docMapper.get(id);
    }

    @Override
    public int deletedById(String id) {
        return docMapper.deletedById(id);
    }

    @Override
    public DocDto selectByIDandUserId(Doc doc) {
        return docMapper.selectByIDandUserId(doc);
    }

    @Override
    public Integer selectPrivilegeById(MemberDto memberDto) {
        return docMapper.selectPrivilegeById(memberDto);
    }
}
