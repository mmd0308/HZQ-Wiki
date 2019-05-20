package com.hzqing.admin.service.space.impl;

import com.hzqing.admin.domain.space.Space;
import com.hzqing.admin.mapper.space.SpaceMapper;
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

    @Override
    public List<Space> selectList(Space space) {
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
}
