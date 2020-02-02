package com.hzqing.admin.common.constants;

/**
 * @author hzqing
 * @date 2019-05-21 15:49
 */
public class Constant {
    private Constant(){
        throw new RuntimeException("常量类，允许实例化");
    }

    /**
     * 超级管理员账号
     */
    public static String ADMIN_USER_NAME = "admin";



    /**
     * API 接口前缀
     */
    public static final String API_URL_PREFIX = "/api";

    /**
     * 不需要权限认证的api接口前缀
     */
    public static final String NO_PERMISSION_API_URL_PREFIX = "/api/wiki/show";



    /**
     * 文章摘要的长度
     */
    public static Integer ARTICLE_DESC_LENGHT = 150;
}
