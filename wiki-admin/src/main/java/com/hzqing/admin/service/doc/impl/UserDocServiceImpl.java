package com.hzqing.admin.service.doc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hzqing.admin.dto.doc.UserDocDto;
import com.hzqing.admin.mapper.doc.UserDocMapper;
import com.hzqing.admin.model.entity.doc.UserDoc;
import com.hzqing.admin.model.entity.system.User;
import com.hzqing.admin.service.doc.IUserDocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hzqing
 * @date 2019-05-20 08:32
 */
@Service
public class UserDocServiceImpl implements IUserDocService {

    @Autowired
    @SuppressWarnings("all")
    private UserDocMapper userDocMapper;

    @Override
    public List<UserDocDto> selectList(UserDoc userDoc) {
        return userDocMapper.selectList(userDoc);
    }

    @Override
    public List<User> selectUserListBySID(int spaceId) {
        return userDocMapper.selectUserListBySID(spaceId);
    }

    @Override
    public int insert(UserDoc userDoc) {
        return userDocMapper.insert(userDoc);
    }

    @Override
    public int update(UserDoc userDoc) {
        return userDocMapper.update(userDoc);
    }

    @Override
    public int deletedById(String id) {
        return userDocMapper.deletedById(id);
    }

    @Override
    public int create(UserDoc userDoc) {
        userDocMapper.insert(userDoc);
        return userDoc.getId();
    }

    @Override
    public UserDoc getByUserIdAndDocId(UserDoc userDoc) {
        return userDocMapper.selectOne(new QueryWrapper<>(userDoc));
    }
}
