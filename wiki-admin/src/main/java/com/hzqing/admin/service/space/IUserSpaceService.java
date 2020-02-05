package com.hzqing.admin.service.space;

import com.hzqing.admin.dto.space.UserSpaceDto;
import com.hzqing.admin.model.entity.space.UserSpace;
import com.hzqing.admin.model.entity.system.User;

import java.util.List;

/**
 * @author hzqing
 * @date 2019-05-20 08:32
 */
public interface IUserSpaceService {

    List<UserSpaceDto> selectList(UserSpace userSpace);

    List<User> selectUserListBySID(int spaceId);

    int insert(UserSpace userSpace);

    int update(UserSpace userSpace);

    int deletedById(String id);

    /**
     * 创建
     * @param userSpace
     */
    void create(UserSpace userSpace);

    /**
     * 获取该空间下面不存在的所有用户
     * @param spaceId
     * @return
     */
    List<User> getUserAllBySpaceId(Integer spaceId);

}
