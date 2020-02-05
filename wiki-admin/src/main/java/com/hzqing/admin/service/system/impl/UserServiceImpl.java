package com.hzqing.admin.service.system.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzqing.admin.common.utils.UserAuthUtils;
import com.hzqing.admin.mapper.system.UserMapper;
import com.hzqing.admin.model.entity.system.User;
import com.hzqing.admin.service.system.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author hzqing
 * @date 2019-05-17 09:35
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    @SuppressWarnings("all")
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> selectList(User user) {
        return userMapper.selectList(new QueryWrapper<>(user));
    }

    @Override
    public Page<User> getPage(int num, int size, User user) {
        IPage<User> page = userMapper.selectPage(new Page<>(num, size), new QueryWrapper<>(user));
        return (Page<User>) page;
    }

    @Override
    public int create(User user) {
        user.setCreateTime(LocalDateTime.now());
        return userMapper.insert(user);
    }

    @Override
    public void modifyById(User user) {
        if (StringUtils.isNotEmpty(user.getPassword())){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        user.setUpdateBy(UserAuthUtils.getUserId());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.updateById(user);
    }

    @Override
    public int removedById(int id) {
        return userMapper.deleteById(id);
    }

    @Override
    public User getById(int id) {
        return userMapper.selectById(id);
    }

    @Override
    public User getByUserName(String username) {
        User user = new User();
        user.setUsername(username);
        return userMapper.selectOne(new QueryWrapper<>(user));
    }

    @Override
    public List<User> getAll() {
        return userMapper.selectList(null);
    }
}
