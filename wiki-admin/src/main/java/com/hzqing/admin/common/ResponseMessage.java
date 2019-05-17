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


    public ResponseMessage responseMessage(int code, Object data, String message, long total) {
        this.setData(data);
        this.setCode(code);
        this.setMessage(message);
        this.setTotal(total);
        return this;
    }

    public ResponseMessage successPage(Object data, long total) {
        return this.responseMessage(200, data, "请求成功", total);
    }


    public ResponseMessage error() {
        return this.responseMessage(500, (Object)null, "服务器请求失败", -1L);
    }

    public ResponseMessage success(Object data) {
        return this.responseMessage(200, data, "请求成功", -1L);
    }

}
