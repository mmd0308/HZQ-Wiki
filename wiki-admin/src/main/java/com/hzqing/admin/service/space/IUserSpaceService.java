package com.hzqing.admin.service.space;

import com.hzqing.admin.domain.space.UserSpace;
import com.hzqing.admin.dto.space.UserSpaceDto;

import java.util.List;

/**
 * @author hzqing
 * @date 2019-05-20 08:32
 */
public interface IUserSpaceService {

    List<UserSpaceDto> selectList(UserSpace userSpace);
}
