package com.hzqing.admin.common.utils;

import com.hzqing.admin.model.dto.system.UserInfo;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 获取用户信息
 * @author hzqing
 * @date 2020-02-03 15:08
 */
public class UserAuthUtils {
    private UserAuthUtils() {
    }

    /**
     * 获取当前请求的用户信息
     * @return
     */
    public static UserInfo getUserInfo(){
        return  (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
