package com.hzqing.admin.service.space.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzqing.admin.domain.space.Space;
import com.hzqing.admin.domain.space.UserSpace;
import com.hzqing.admin.dto.space.SpaceDto;
import com.hzqing.admin.mapper.space.SpaceMapper;
import com.hzqing.admin.mapper.space.UserSpaceMapper;
import com.hzqing.admin.service.space.ISpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author hzqing
 * @date 2019-05-20 08:32
 */
@Service
public class SpaceServiceImpl implements ISpaceService {

    @Autowired
    private SpaceMapper spaceMapper;

    /**
     * 用户更空间中间关系
     */
    @Autowired
    private UserSpaceMapper userSpaceMapper;

    @Override
    public List<SpaceDto> selectList(Space space) {
        return spaceMapper.selectList(space);
    }

    @Override
    public int insert(Space space) {
       spaceMapper.insert(space);
        // 新增空间的时候，插入拥有者
        UserSpace userSpace = new UserSpace();
        userSpace.setUserId(space.getCreateBy());
        userSpace.setCreateTime(LocalDateTime.now());
        userSpace.setSpaceId(space.getId());
        userSpace.setPrivilege(0);
        userSpace.setCreateBy(space.getCreateBy());
        userSpace.setCreateTime(LocalDateTime.now());
        userSpaceMapper.insert(userSpace);
        return space.getId();
    }

    @Override
    public int update(Space space) {
        return spaceMapper.update(space);
    }

    @Override
    public int deletedById(String id) {
        return spaceMapper.deletedById(id);
    }

    @Override
    public List<SpaceDto> selectListByUserId(Space space) {
        return spaceMapper.selectListByUserId(space);
    }

    @Override
    public SpaceDto get(int id) {
        return spaceMapper.get(id);
    }

    @Override
    public Page<Space> getPage(int num, int size, Space space) {
        return (Page<Space>) spaceMapper.selectPage(new Page<>(num,size),new QueryWrapper<>(space));
    }
}
