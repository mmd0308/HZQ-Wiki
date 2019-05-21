package com.hzqing.admin.mapper.system;

import com.hzqing.admin.domain.system.User;

import java.util.List;

/**
 * @author hzqing
 * @date 2019-05-17 09:40
 */
public interface UserMapper {

    List<User> selectList(User user);

    int update(User user);

    int insert(User user);

    int deletedById(String id);

    User selectByUserName(String username);
}
