package com.hzqing.admin.common.constants;

/**
 * @author hzqing
 * @date 2019-08-11 15:30
 */
public enum RestResultCodeConstants {

    SUCCESS                         ("200","成功！"),
    SYS_ERROR                       ("10500", "系统错误！");

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
