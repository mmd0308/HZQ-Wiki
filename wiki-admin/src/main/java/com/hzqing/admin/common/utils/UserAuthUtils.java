package com.hzqing.admin.common.utils;

import com.hzqing.admin.common.constants.ConstantSecurity;
import com.hzqing.admin.model.dto.system.UserInfo;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;

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

    /**
     * 获取当前请求的用户id
     * @return
     */
    public static Integer getUserId(){
        return getUserInfo().getId();
    }

    /**
     * 判断用户是否登陆
     * @return
     */
    public static boolean isLogin(HttpServletRequest request){
        // 平台token
        String token = request.getHeader(ConstantSecurity.TOKEN_KEY);

        if (SecurityContextHolder.getContext().getAuthentication() instanceof UsernamePasswordAuthenticationToken
            && (token != null && token.startsWith(ConstantSecurity.TOKEN_PREFIX))){
            return true;
        }
        return false;
    }

}
