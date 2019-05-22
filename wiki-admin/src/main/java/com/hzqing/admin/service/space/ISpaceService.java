package com.hzqing.admin.service.space;

import com.hzqing.admin.domain.space.Space;
import com.hzqing.admin.dto.space.SpaceDto;

import java.util.List;

/**
 * @author hzqing
 * @date 2019-05-20 08:32
 */
public interface ISpaceService {
    List<Space> selectList(Space space);

    int insert(Space space);

    int update(Space space);

    int deletedById(String id);

    /**
     * 根据用户id，获取该用户的所有空间
     * @param space
     * @return
     */
    List<SpaceDto> selectListByUserId(Space space);
}
