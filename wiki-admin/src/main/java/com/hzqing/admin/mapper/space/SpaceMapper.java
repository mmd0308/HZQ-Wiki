package com.hzqing.admin.mapper.space;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hzqing.admin.dto.space.SpaceDto;
import com.hzqing.admin.model.entity.space.Space;

import java.util.List;

public interface SpaceMapper  extends BaseMapper<Space> {
    List<SpaceDto> selectList(Space space);

    List<SpaceDto> selectListByUserId(Space space);

}
