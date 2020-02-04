package com.hzqing.admin.service.doc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzqing.admin.dto.doc.MemberDto;
import com.hzqing.admin.mapper.doc.DocMapper;
import com.hzqing.admin.model.dto.doc.DocDto;
import com.hzqing.admin.model.entity.doc.Doc;
import com.hzqing.admin.model.entity.doc.UserDoc;
import com.hzqing.admin.model.enums.doc.UserDocPrivilege;
import com.hzqing.admin.service.doc.IDocService;
import com.hzqing.admin.service.doc.IUserDocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private IUserDocService userDocService;


    @Override
    public List<Doc> selectList(Doc doc) {
        return docMapper.selectList(doc);
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
    public DocDto selectByIDandUserId(Doc doc) {
        return docMapper.selectByIDandUserId(doc);
    }

    @Override
    public Integer selectPrivilegeById(MemberDto memberDto) {
        return docMapper.selectPrivilegeById(memberDto);
    }

    @Override
    public Page<DocDto> getPageBySpaceOrLevel(int num, int size, Doc doc) {
        IPage<DocDto> docDtoIPage =  docMapper.selectPageBySpaceOrLevel(new Page<>(num, size), doc);
        return (Page<DocDto>) docDtoIPage;
    }

    @Override
    public Page<Doc> getPage(int num, int size, Doc doc) {
        return (Page<Doc>) docMapper.selectPage(new Page<>(num, size), new QueryWrapper<Doc>(doc));
    }

    @Override
    @Transactional
    public int create(Doc doc) {
        docMapper.insert(doc);
        // 新增用户和文档关系
        UserDoc userDoc = new UserDoc();
        userDoc.setDocId(doc.getId());
        userDoc.setPrivilege(UserDocPrivilege.OWNER);
        userDoc.setCreateTime(LocalDateTime.now());
        userDocService.create(userDoc);

        return doc.getId();
    }

    @Override
    public void modifyById(Doc doc) {
        docMapper.updateById(doc);
    }

    @Override
    public int removedById(int id) {
        return docMapper.deleteById(id);
    }

    @Override
    public Doc getById(int id) {
        return docMapper.selectById(id);
    }

    @Override
    public Page<DocDto> getShowPageByDto(int num, int size, DocDto docDto) {
        IPage<DocDto> docDtoIPage =  docMapper.selectShowPageByDto(new Page<>(num, size), docDto);
        return (Page<DocDto>) docDtoIPage;
    }
}
