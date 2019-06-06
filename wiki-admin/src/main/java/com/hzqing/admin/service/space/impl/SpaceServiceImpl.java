package com.hzqing.admin.service.space.impl;

import com.hzqing.admin.domain.space.Space;
import com.hzqing.admin.dto.space.SpaceDto;
import com.hzqing.admin.mapper.space.SpaceMapper;
import com.hzqing.admin.mapper.space.UserSpaceMapper;
import com.hzqing.admin.service.space.ISpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return spaceMapper.insert(space);
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
}
