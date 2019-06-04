package com.hzqing.admin.common;

import lombok.Data;

/**
 * @author hzqing
 * @date 2019-05-17 10:26
 */
@Data
public class ResponseMessage {
    /**
     * 返回状态编码
     */
    private int code;

    /**
     * 返回的数据
     */
    private Object data;

    /**
     * 分页的总数
     */
    private long total;

    /**
     * 响应信息
     */
    private String message;

    public ResponseMessage() {
    }

    public ResponseMessage(int code, Object data, String message,long total) {
        this.code = code;
        this.data = data;
        this.total = total;
        this.message = message;
    }


    public static ResponseMessage successPage(Object data, long total) {
        return new ResponseMessage(200, data, "请求成功", total);
    }


    public static ResponseMessage error() {
        return new ResponseMessage(500, (Object)null, "服务器请求失败", -1L);
    }

    public static ResponseMessage success(Object data) {
        return new ResponseMessage(200, data, "请求成功", -1L);
    }

}
