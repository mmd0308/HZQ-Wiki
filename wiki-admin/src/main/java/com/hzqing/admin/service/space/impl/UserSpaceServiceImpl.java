package com.hzqing.admin.service.space.impl;

import com.hzqing.admin.domain.space.Space;
import com.hzqing.admin.domain.space.UserSpace;
import com.hzqing.admin.domain.system.User;
import com.hzqing.admin.dto.space.SpaceDto;
import com.hzqing.admin.dto.space.UserSpaceDto;
import com.hzqing.admin.mapper.space.SpaceMapper;
import com.hzqing.admin.mapper.space.UserSpaceMapper;
import com.hzqing.admin.service.space.ISpaceService;
import com.hzqing.admin.service.space.IUserSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hzqing
 * @date 2019-05-20 08:32
 */
@Service
public class UserSpaceServiceImpl implements IUserSpaceService {

    @Autowired
    private UserSpaceMapper userSpaceMapper;

    @Override
    public List<UserSpaceDto> selectList(UserSpace userSpace) {
        return userSpaceMapper.selectList(userSpace);
    }

    @Override
    public List<User> selectUserListBySID(int spaceId) {
        return userSpaceMapper.selectUserListBySID(spaceId);
    }

    @Override
    public int insert(UserSpace userSpace) {
        return userSpaceMapper.insert(userSpace);
    }

    @Override
    public int update(UserSpace userSpace) {
        return userSpaceMapper.update(userSpace);
    }

    @Override
    public int deletedById(String id) {
        return userSpaceMapper.deletedById(id);
    }
}
