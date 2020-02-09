package com.hzqing.admin.service.space;

import com.hzqing.admin.model.dto.space.UserSpaceDto;
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
     * @return
     */
    int create(UserSpace userSpace);

    /**
     * 获取该空间下面不存在的所有用户
     * @param spaceId
     * @return
     */
    List<User> getUserAllBySpaceId(Integer spaceId);

    /**
     * 根据空间id，获取所有的数据
     * @param spaceId
     * @return
     */
    List<UserSpaceDto> getListAllBySpaceId(Integer spaceId);

    /**
     * 根据id，删除数据
     * @param id
     * @return
     */
    int removedById(Integer id);

    /**
     * 根据条件，获取所有的数据
     * @param userSpace
     * @return
     */
    List<UserSpace> getListAll(UserSpace userSpace);
}
