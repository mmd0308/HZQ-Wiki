package com.hzqing.admin.service.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzqing.admin.model.entity.system.User;

import java.util.List;

/**
 * @author hzqing
 * @date 2019-05-17 09:32
 */
public interface IUserService {
    
    List<User> selectList(User user);


    /**
     * 获取用户分页信息
     * @param num
     * @param size
     * @param user
     * @return
     */
    Page<User> getPage(int num, int size, User user);

    /**
     * 创建用户，返回用户的id
     * @param user
     * @return
     */
    int create(User user);

    /**
     * 根据id修改用户
     * @param user
     */
    void modifyById(User user);

    /**
     * 根据id，删除用户
     * @param id
     * @return
     */
    int removedById(int id);

    /**
     * 根据id，获取用户信息
     * @param id
     * @return
     */
    User getById(int id);

    /**
     * 根据用户名获取用户信息
     * @param username
     * @return
     */
    User getByUserName(String username);
}
