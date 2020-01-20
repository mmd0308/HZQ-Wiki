package com.hzqing.admin.service.system.impl;

import com.hzqing.admin.model.dto.system.UserInfo;
import com.hzqing.admin.model.entity.system.User;
import com.hzqing.admin.service.system.IAuthService;
import com.hzqing.admin.service.system.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hzqing
 * @date 2019-05-21 09:51
 */
@Service
public class AuthServiceImpl implements IAuthService {

    @Autowired
    private IUserService userService;

    @Override
    public UserInfo getUserInfo(String userName) {
        User user = userService.getByUserName(userName);
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(user,userInfo);
        userInfo.setRoles(new String[]{"ROLE_1","ROLE_2"});
        userInfo.setResources(new String[]{"RES_1","RES_2"});
        return userInfo;
    }
}
