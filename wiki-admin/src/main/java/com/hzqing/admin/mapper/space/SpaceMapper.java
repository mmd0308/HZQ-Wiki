package com.hzqing.admin.mapper.space;

import com.hzqing.admin.domain.space.Space;

import java.util.List;

public interface SpaceMapper {
    List<Space> selectList(Space space);

    int update(Space space);

    int insert(Space space);

    int deletedById(String id);

    List<Space> selectListByUserId(Space space);
}
