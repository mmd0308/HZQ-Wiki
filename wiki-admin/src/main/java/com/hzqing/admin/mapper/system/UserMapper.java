package com.hzqing.admin.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hzqing.admin.model.entity.system.User;

import java.util.List;

/**
 * @author hzqing
 * @date 2019-05-17 09:40
 */
public interface UserMapper extends BaseMapper<User> {

    List<User> selectList(User user);

    int update(User user);

    int insert(User user);

    int deletedById(String id);

    User get(int id);
}
