package com.hzqing.admin.service.space.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzqing.admin.common.utils.UserAuthUtils;
import com.hzqing.admin.mapper.space.SpaceMapper;
import com.hzqing.admin.model.dto.space.SpaceDto;
import com.hzqing.admin.model.entity.space.Space;
import com.hzqing.admin.model.entity.space.UserSpace;
import com.hzqing.admin.model.enums.space.UserSpacePrivilege;
import com.hzqing.admin.service.space.ISpaceService;
import com.hzqing.admin.service.space.IUserSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author hzqing
 * @date 2019-05-20 08:32
 */
@Service
public class SpaceServiceImpl implements ISpaceService {

    @Autowired
    @SuppressWarnings("all")
    private SpaceMapper spaceMapper;

    /**
     * 用户跟空间中间关系
     */
    @Autowired
    private IUserSpaceService userSpaceService;

    @Override
    public List<SpaceDto> selectList(Space space) {
        return spaceMapper.selectList(space);
    }



    @Override
    public List<SpaceDto> selectListByUserId(Space space) {
        return spaceMapper.selectListByUserId(space);
    }

    @Override
    public Page<SpaceDto> getPage(int num, int size, SpaceDto spaceDto) {
        IPage<SpaceDto> spaceDtoIPage = spaceMapper.selectPageList(new Page<>(num,size),spaceDto);
        return (Page<SpaceDto>) spaceDtoIPage;
    }

    @Override
    @Transactional
    public int create(Space space) {
        space.setCreateBy(UserAuthUtils.getUserId());
        space.setCreateTime(LocalDateTime.now());
        spaceMapper.insert(space);

        // 新增空间的时候，插入拥有者
        UserSpace userSpace = new UserSpace();
        userSpace.setUserId(space.getCreateBy());
        userSpace.setCreateTime(LocalDateTime.now());
        userSpace.setSpaceId(space.getId());
        userSpace.setPrivilege(UserSpacePrivilege.OWNER);
        userSpace.setCreateBy(space.getCreateBy());
        userSpace.setCreateTime(LocalDateTime.now());

        userSpaceService.create(userSpace);
        return space.getId();
    }

    @Override
    public void modifyById(Space space) {
        space.setUpdateTime(LocalDateTime.now());
        spaceMapper.updateById(space);
    }

    @Override
    public int removedById(int id) {
        return spaceMapper.deleteById(id);
    }
}
