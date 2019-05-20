package com.hzqing.admin.service.space;

import com.hzqing.admin.domain.space.Space;

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
}
