package com.hzqing.admin.mapper.space;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hzqing.admin.model.dto.space.UserSpaceDto;
import com.hzqing.admin.model.entity.space.UserSpace;
import com.hzqing.admin.model.entity.system.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hzqing
 * @date 2019-05-21 14:47
 */
@Repository
public interface UserSpaceMapper extends BaseMapper<UserSpace> {

    List<UserSpaceDto> selectList(UserSpace userSpace);

    List<User> selectUserListBySID(int spaceId);

    List<UserSpaceDto> selectListBySpaceId(Integer spaceId);
}
