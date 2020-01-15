package com.hzqing.admin.mapper.space;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hzqing.admin.domain.space.Space;
import com.hzqing.admin.dto.space.SpaceDto;

import java.util.List;

public interface SpaceMapper  extends BaseMapper<Space> {
    List<SpaceDto> selectList(Space space);

    int update(Space space);

    int insert(Space space);

    int deletedById(String id);

    List<SpaceDto> selectListByUserId(Space space);

    SpaceDto get(int id);
}
