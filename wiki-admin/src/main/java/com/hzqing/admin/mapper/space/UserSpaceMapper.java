package com.hzqing.admin.mapper.space;

import com.hzqing.admin.domain.space.UserSpace;
import com.hzqing.admin.domain.system.User;
import com.hzqing.admin.dto.space.UserSpaceDto;

import java.util.List;

/**
 * @author hzqing
 * @date 2019-05-21 14:47
 */
public interface UserSpaceMapper {

    List<UserSpaceDto> selectList(UserSpace userSpace);

    List<User> selectUserListBySID(int spaceId);

    int insert(UserSpace userSpace);

    int update(UserSpace userSpace);

    int deletedById(String id);
}
