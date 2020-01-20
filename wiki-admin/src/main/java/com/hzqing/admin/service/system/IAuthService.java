package com.hzqing.admin.service.system;


import com.hzqing.admin.model.dto.system.UserInfo;

/**
 * @author hzqing
 * @date 2019-05-21 09:51
 */
public interface IAuthService {
    /**
     * 根据用户名获取用户的详细信息
     * @param userName
     * @return
     */
    UserInfo getUserInfo(String userName);

}
