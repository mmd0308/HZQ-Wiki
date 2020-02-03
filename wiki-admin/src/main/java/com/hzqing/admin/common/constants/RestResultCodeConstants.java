package com.hzqing.admin.common.constants;

/**
 *
 * @author hzqing
 * @date 2019-08-11 15:30
 */
public enum RestResultCodeConstants {


    SUCCESS("200","成功"),

    PARAMS_MISSING("10000","参数缺失"),

    UNAUTHORIZED("40100","未授权"),

    TOKEN_ERROR("40110", "Token 错误"),
    TOKEN_EXPIRE("40120", "Token 过期"),

    ARTICLE_UNAUTHORIZED("41100", "文章管理模块权限不足"),
    ARTICLE_NO_RELEASE("41110","该文章没有发布，您没有权限访问"),

    DOC_UNAUTHORIZED("41200", "文档管理模块权限不足"),
    DOC_NO_PUBLIC("41210","该文档是私有文档，您没有权限访问"),

    SYS_ERROR("50000", "系统出错");




    private String code;

    private String msg;

    RestResultCodeConstants(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
