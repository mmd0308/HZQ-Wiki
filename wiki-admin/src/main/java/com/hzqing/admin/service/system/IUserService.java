package com.hzqing.admin.service.system;

import com.hzqing.admin.domain.system.User;

import java.util.List;

/**
 * @author hzqing
 * @date 2019-05-17 09:32
 */
public interface IUserService {
    
    List<User> selectList(User user);
}
