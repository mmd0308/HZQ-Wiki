package com.hzqing.admin.common;

/**
 * @author hzqing
 * @date 2019-05-21 09:59
 */
public class ConstantSecurity {
    private ConstantSecurity(){
        throw new RuntimeException("常量类，允许实例化");
    }


    public static final String TOKEN_PREFIX = "Bearer ";

    public static final String TOKEN_KEY = "Authorization";

    public static final String USER_ID = "USER_ID";

    public static final String JWT_SECRET = "hzqing.com";

    public static final int JWT_EXPIRATION = 18000;


}
