package com.hzqing.admin.mapper.system;

import com.hzqing.admin.domain.system.User;

import java.util.List;

/**
 * @author hzqing
 * @date 2019-05-17 09:40
 */
public interface UserMapper {

    List<User> selectList(User user);

}
