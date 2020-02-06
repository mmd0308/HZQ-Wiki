package com.hzqing.admin.service.space.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hzqing.admin.common.utils.UserAuthUtils;
import com.hzqing.admin.mapper.space.UserSpaceMapper;
import com.hzqing.admin.model.dto.space.UserSpaceDto;
import com.hzqing.admin.model.entity.space.UserSpace;
import com.hzqing.admin.model.entity.system.User;
import com.hzqing.admin.service.space.IUserSpaceService;
import com.hzqing.admin.service.system.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hzqing
 * @date 2019-05-20 08:32
 */
@Service
public class UserSpaceServiceImpl implements IUserSpaceService {

    @Autowired
    @SuppressWarnings("all")
    private UserSpaceMapper userSpaceMapper;

    @Autowired
    private IUserService userService;

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
        return userSpaceMapper.updateById(userSpace);
    }

    @Override
    public int deletedById(String id) {
        return userSpaceMapper.deleteById(id);
    }

    @Override
    public int create(UserSpace userSpace) {
        userSpace.setCreateBy(UserAuthUtils.getUserId());
        userSpace.setCreateTime(LocalDateTime.now());
        userSpaceMapper.insert(userSpace);
        return userSpace.getId();
    }

    @Override
    public List<User> getUserAllBySpaceId(Integer spaceId) {
        List<UserSpace> userSpaceList = userSpaceMapper.selectList(new QueryWrapper<UserSpace>().eq("space_id", spaceId));
        List<Integer> userIds = new ArrayList<>(userSpaceList.size());
        userSpaceList.forEach(userSpace -> {
            userIds.add(userSpace.getUserId());
        });

        // 获取到所有的用户
        List<User> userList = userService.getAll();
        // 删除该空间已经拥有的用户
        userList.removeIf(user -> {
            return userIds.contains(user.getId());
        });
        return userList;
    }

    @Override
    public List<UserSpaceDto> getListAllBySpaceId(Integer spaceId) {
        return userSpaceMapper.selectListBySpaceId(spaceId);
    }

    @Override
    public int removedById(Integer id) {
        return userSpaceMapper.deleteById(id);
    }
}
