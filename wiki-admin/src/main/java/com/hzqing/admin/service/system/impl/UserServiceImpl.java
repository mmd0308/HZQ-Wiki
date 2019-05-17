package com.hzqing.admin.service.system.impl;

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
}
