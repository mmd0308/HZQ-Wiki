package com.hzqing.admin.service.system.impl;

import com.hzqing.admin.domain.space.Space;
import com.hzqing.admin.domain.system.User;
import com.hzqing.admin.mapper.system.UserMapper;
import com.hzqing.admin.service.system.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hzqing
 * @date 2019-05-17 09:35
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> selectList(User user) {
        return userMapper.selectList(user);
    }

    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    public int update(User user) {
        return userMapper.update(user);
    }

    @Override
    public int deletedById(String id) {
        return userMapper.deletedById(id);
    }

    @Override
    public User selectByUserName(String username) {
        return userMapper.selectByUserName(username);
    }
}
