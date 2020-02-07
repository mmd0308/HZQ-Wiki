package com.hzqing.admin.common.utils;

import com.hzqing.admin.common.constants.Constant;
import com.hzqing.admin.common.constants.ConstantSecurity;
import com.hzqing.admin.common.constants.RestResultCodeConstants;
import com.hzqing.admin.common.exception.BaseException;
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
     * 当前登陆账号是否是admin
     * true 是
     * false 否
     * @return
     */
    public static Boolean isAdmin(){
        if (getUserInfo().getUsername().equals(Constant.ADMIN_USER_NAME)){
            return true;
        }
        return false;
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
     * true 登陆
     * false 没有登陆
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

    /**
     * 用户如果没有登陆，抛出异常，否则返回true
     * @return
     */
    public static boolean isLoginException(HttpServletRequest request){
       if (!isLogin(request)){
            throw new BaseException(
                    RestResultCodeConstants.TOKEN_MISSING.getCode(),
                    RestResultCodeConstants.TOKEN_MISSING.getMsg()
            );
       }
        return true;
    }

}
